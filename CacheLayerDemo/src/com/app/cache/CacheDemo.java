package com.app.cache;

import java.util.concurrent.*;
import java.time.*;

public class CacheDemo {

    // A dummy Product class
    static class Product {
        String id;
        double price;
        LocalDateTime lastUpdated;

        Product(String id, double price) {
            this.id = id;
            this.price = price;
            this.lastUpdated = LocalDateTime.now();
        }

        boolean isStale() {
            return lastUpdated.plusSeconds(10).isBefore(LocalDateTime.now());
        }
    }

    // Simulate fetching updated product data
    static Product fetchUpdatedProduct(String id) {
        System.out.println("Refreshing product: " + id);
        return new Product(id, Math.random() * 1000);
    }

    public static void main(String[] args) {

        ConcurrentHashMap<String, Product> productCache = new ConcurrentHashMap<>();

        // Load initial products
        productCache.put("P1", new Product("P1", 500));
        productCache.put("P2", new Product("P2", 800));
        productCache.put("P3", new Product("P3", 300));

        // Reader threads simulating high traffic requests
        ExecutorService readers = Executors.newFixedThreadPool(5);

        Runnable readerTask = () -> {
            while (true) {
                productCache.forEach((id, p) -> {
                    System.out.println(Thread.currentThread().getName() +
                            " reading " + id + " : " + p.price);
                });
                try { Thread.sleep(2000); } catch (InterruptedException e) {}
            }
        };

        for (int i = 0; i < 3; i++) {
            readers.submit(readerTask);
        }

        // Auto-refresh every 5 seconds (instead of 5 mins for demo)
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            productCache.forEach((id, product) -> {
                if (product.isStale()) {
                    productCache.put(id, fetchUpdatedProduct(id));
                }
            });
        }, 0, 5, TimeUnit.SECONDS);
    }
}
