package com.smartship.service;

import com.smartship.exception.*;
import com.smartship.model.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class DeliveryServiceImpl implements DeliveryService {

    private final Map<String, Agent> agents = new ConcurrentHashMap<>();
    private final Map<String, Route> routes = new ConcurrentHashMap<>();
    private final PriorityQueue<PackageItem> packageQueue = new PriorityQueue<>();

    @Override
    public void addAgent(Agent a) throws DuplicateEntryException {
        if (agents.containsKey(a.getId())) throw new DuplicateEntryException("Agent exists: " + a.getId());
        agents.put(a.getId(), a);
    }

    @Override
    public void addRoute(Route r) throws DuplicateEntryException {
        if (routes.containsKey(r.getId())) throw new DuplicateEntryException("Route exists: " + r.getId());
        routes.put(r.getId(), r);
    }

    @Override
    public void addPackage(PackageItem p) throws DuplicateEntryException, InvalidPackageException {
        if (p.getId() == null || p.getDestination() == null)
            throw new InvalidPackageException("Invalid package data");
        if (packageQueue.stream().anyMatch(pkg -> pkg.getId().equals(p.getId())))
            throw new DuplicateEntryException("Duplicate package ID: " + p.getId());
        packageQueue.offer(p);
    }

    @Override
    public void assignAllPackages() throws Exception {
        while (!packageQueue.isEmpty()) {
            PackageItem pkg = packageQueue.poll();
            String dest = pkg.getDestination();

            // Route validation
            boolean routeAvailable = routes.values().stream()
                    .anyMatch(r -> r.isAvailable() && r.getCities().contains(dest));
            if (!routeAvailable)
                throw new RouteUnavailableException("No route to " + dest);

            // Find agents in that destination city, sorted by load
            List<Agent> availableAgents = agents.values().stream()
                    .filter(a -> a.getCity().equalsIgnoreCase(dest))
                    .sorted(Comparator.comparingInt(Agent::getLoad))
                    .collect(Collectors.toList());

            if (availableAgents.isEmpty())
                throw new AgentNotAvailableException("No free agent in city " + dest);

            Agent chosen = availableAgents.get(0); // least loaded
            chosen.assignPackage(pkg);
            System.out.println("Assigned " + pkg.getId() + " -> " + chosen.getName());
        }
    }

    @Override
    public void displayStatus() {
        System.out.println("\n=== Agent Summary ===");
        agents.values().forEach(System.out::println);
    }
}
