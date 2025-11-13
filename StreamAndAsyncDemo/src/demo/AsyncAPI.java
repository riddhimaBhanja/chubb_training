package demo;

import java.util.concurrent.*;

public class AsyncAPI {
    public static void main(String[] args) {
        CompletableFuture<String> fetchUser = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "User data fetched ✅";
        });

        CompletableFuture<String> fetchOrders = CompletableFuture.supplyAsync(() -> {
            delay(1500);
            return "Order data fetched ✅";
        });

        CompletableFuture<Void> all = CompletableFuture.allOf(fetchUser, fetchOrders);
        all.join();

        fetchUser.thenAccept(System.out::println);
        fetchOrders.thenAccept(System.out::println);
        System.out.println("Processing complete 🚀");
    }

    private static void delay(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
