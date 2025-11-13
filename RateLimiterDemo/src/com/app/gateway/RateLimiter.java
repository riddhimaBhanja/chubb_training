package com.app.gateway;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RateLimiter {

    private final ConcurrentHashMap<String, AtomicInteger> rateMap = new ConcurrentHashMap<>();
    private final int MAX_REQUESTS = 5; // limit per user (for demo)
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public RateLimiter() {
        // Reset all counters every 10 seconds (demo interval)
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("\nResetting rate map...");
            rateMap.clear();
        }, 10, 10, TimeUnit.SECONDS);
    }

    public boolean allowRequest(String userId) {
        rateMap.putIfAbsent(userId, new AtomicInteger(0));
        int count = rateMap.get(userId).incrementAndGet();
        return count <= MAX_REQUESTS;
    }

    public static void main(String[] args) {
        RateLimiter limiter = new RateLimiter();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Simulate multiple users making requests
        String[] users = {"userA", "userB", "userC"};

        Runnable requestTask = () -> {
            for (String user : users) {
                boolean allowed = limiter.allowRequest(user);
                if (allowed)
                    System.out.println(Thread.currentThread().getName() + " ✅ Allowed for " + user);
                else
                    System.out.println(Thread.currentThread().getName() + " ❌ Denied for " + user);
                try { Thread.sleep(500); } catch (InterruptedException e) {}
            }
        };

        // Launch multiple concurrent threads
        for (int i = 0; i < 10; i++) {
            executor.submit(requestTask);
        }

        executor.shutdown();
    }
}
