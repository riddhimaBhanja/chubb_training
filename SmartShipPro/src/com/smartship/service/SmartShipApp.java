package com.smartship.service;

import com.smartship.exception.*;
import com.smartship.model.*;
import com.smartship.service.DeliveryServiceImpl;

import java.util.Arrays;

public class SmartShipApp {
    public static void main(String[] args) {
        DeliveryServiceImpl service = new DeliveryServiceImpl();

        try {
            // Add routes
            service.addRoute(new Route("R1", Arrays.asList("Delhi", "Mumbai", "Bangalore")));
            service.addRoute(new Route("R2", Arrays.asList("Chennai", "Pune", "Hyderabad")));

            // Add agents (city, capacity)
            service.addAgent(new DeliveryAgent("A1", "Ravi", "Mumbai", 2));
            service.addAgent(new DeliveryAgent("A2", "Sneha", "Bangalore", 2));
            service.addAgent(new DeliveryAgent("A3", "Arjun", "Delhi", 1));

            // Add packages (id, source, destination, weight, priority)
            service.addPackage(new PackageItem("P1", "Delhi", "Mumbai", 5.0, 1));
            service.addPackage(new PackageItem("P2", "Delhi", "Bangalore", 4.0, 2));
            service.addPackage(new PackageItem("P3", "Delhi", "Mumbai", 3.0, 3));
            service.addPackage(new PackageItem("P4", "Delhi", "Delhi", 2.0, 1)); // self-city delivery

            service.assignAllPackages();
            service.displayStatus();

        } catch (DuplicateEntryException | InvalidPackageException | OverloadException |
                 AgentNotAvailableException | RouteUnavailableException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
