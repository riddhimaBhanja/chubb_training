package com.app.main;

import java.util.Queue;
import java.util.concurrent.*;

public class OrderProcessorDemo {

    // Dummy Order class
    static class Order {
        int id;
        double amount;

        Order(int id, double amount) {
            this.id = id;
            this.amount = amount;
        }
    }

    // Simulate order processing
    static void processOrder(Order order) {
        System.out.println("Processing Order ID: " + order.id + ", Amount: " + order.amount);
        try {
            Thread.sleep(100); // simulate some delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Queue<Order> orderQueue = new ConcurrentLinkedQueue<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Step 1: Generate thousands of fake orders
        for (int i = 1; i <= 100; i++) {
            orderQueue.add(new Order(i, Math.random() * 1000));
        }

        // Step 2: Create worker threads that continuously poll the queue
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                while (!orderQueue.isEmpty()) {
                    Order order = orderQueue.poll();
                    if (order != null) processOrder(order);
                }
            });
        }

        // Step 3: Shutdown after all tasks finish
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nAll orders processed successfully!");
    }
}
