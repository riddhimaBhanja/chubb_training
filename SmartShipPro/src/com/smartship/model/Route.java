package com.smartship.model;

import java.util.List;

public class Route {
    private final String id;
    private final List<String> cities;
    private boolean available = true;

    public Route(String id, List<String> cities) {
        this.id = id;
        this.cities = cities;
    }

    public String getId() { return id; }
    public List<String> getCities() { return cities; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "Route{" + id + ", cities=" + cities + ", available=" + available + "}";
    }
}
