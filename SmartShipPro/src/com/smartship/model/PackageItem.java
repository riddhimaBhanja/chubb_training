package com.smartship.model;

import java.util.Objects;

public class PackageItem implements Comparable<PackageItem> {
    private final String id;
    private final String source;
    private final String destination;
    private final double weight;
    private final int priority; // 1 = High, 2 = Medium, 3 = Low
    private PackageStatus status;

    public PackageItem(String id, String source, String destination, double weight, int priority) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
        this.priority = priority;
        this.status = PackageStatus.PENDING;
    }

    public String getId() { return id; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public double getWeight() { return weight; }
    public int getPriority() { return priority; }
    public PackageStatus getStatus() { return status; }
    public void setStatus(PackageStatus status) { this.status = status; }

    @Override
    public int compareTo(PackageItem other) {
        return Integer.compare(this.priority, other.priority); // lower = higher priority
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PackageItem)) return false;
        PackageItem that = (PackageItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "PackageItem{" + id + ", dest=" + destination + ", priority=" + priority + ", status=" + status + "}";
    }
}
