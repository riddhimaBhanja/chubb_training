package com.app.logs;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.*;

public class LogAggregator {

    static BlockingQueue<String> logQueue = new LinkedBlockingQueue<>();
    static ExecutorService executor = Executors.newCachedThreadPool();
    static volatile boolean running = true;

    public static void main(String[] args) {

        // Consumer thread — writes logs to file
        executor.submit(() -> {
            try (FileWriter writer = new FileWriter("aggregated_logs.txt", true)) {
                while (running || !logQueue.isEmpty()) {
                    String log = logQueue.take();
                    writer.write(LocalDateTime.now() + " " + log + "\n");
                    writer.flush();
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });

        // Simulate multiple producer threads
        for (int i = 1; i <= 3; i++) {
            final int serverId = i;
            executor.submit(() -> {
                try {
                    for (int j = 1; j <= 10; j++) {
                        String log = "Server-" + serverId + " :: INFO :: Request #" + j;
                        logQueue.put(log);
                        System.out.println("Produced: " + log);
                        Thread.sleep(300); // simulate log frequency
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Graceful shutdown after all logs are produced
        executor.submit(() -> {
            try {
                Thread.sleep(5000);
                running = false;
                executor.shutdown();
                System.out.println("\n✅ All logs processed. Check aggregated_logs.txt");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
