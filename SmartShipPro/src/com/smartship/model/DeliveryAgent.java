package com.smartship.model;

import com.smartship.exception.OverloadException;

public class DeliveryAgent extends Agent {
    public DeliveryAgent(String id, String name, String city, int capacity) {
        super(id, name, city, capacity);
    }

    @Override
    public void assignPackage(PackageItem p) throws OverloadException {
        if (assigned.size() >= capacity) throw new OverloadException("Agent " + name + " overloaded!");
        assigned.add(p);
        p.setStatus(PackageStatus.ASSIGNED);
    }
}
