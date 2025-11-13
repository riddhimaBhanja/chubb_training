package com.smartship.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Agent {
    protected final String id;
    protected final String name;
    protected final String city;
    protected final int capacity;
    protected final List<PackageItem> assigned = new ArrayList<>();

    public Agent(String id, String name, String city, int capacity) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.capacity = capacity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public int getCapacity() { return capacity; }
    public int getLoad() { return assigned.size(); }

    public abstract void assignPackage(PackageItem p) throws Exception;

    @Override
    public String toString() {
        return "Agent{" + id + ":" + name + ", city=" + city + ", load=" + getLoad() + "/" + capacity + "}";
    }
}
