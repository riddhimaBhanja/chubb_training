package com.app.trading;

import java.util.*;
import java.util.concurrent.*;

// Represents a simple Order
class Order {
    enum Type { BUY, SELL }
    String trader;
    double price;
    int quantity;
    Type type;

    Order(String trader, double price, int quantity, Type type) {
        this.trader = trader;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    @Override
    public String toString() {
        return type + " | Trader: " + trader + " | Price: " + price + " | Qty: " + quantity;
    }
}

public class TradingEngine {

    // Order books (sorted maps)
    private static final ConcurrentSkipListMap<Double, List<Order>> buyBook =
            new ConcurrentSkipListMap<>(Comparator.reverseOrder()); // high → low
    private static final ConcurrentSkipListMap<Double, List<Order>> sellBook =
            new ConcurrentSkipListMap<>(); // low → high

    // Lock for safe matching
    private static final Object matchLock = new Object();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(6);

        // Producer threads: simulate traders placing orders
        for (int i = 1; i <= 3; i++) {
            final int traderId = i;
            executor.submit(() -> placeOrders("Trader-" + traderId));
        }

        // Consumer thread: matches orders
        executor.submit(TradingEngine::matchOrders);

        executor.shutdown();
    }

    // Simulate random buy/sell orders
    private static void placeOrders(String trader) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            double price = 90 + random.nextInt(20); // between 90-110
            int qty = 1 + random.nextInt(5);
            Order.Type type = random.nextBoolean() ? Order.Type.BUY : Order.Type.SELL;
            Order order = new Order(trader, price, qty, type);

            ConcurrentSkipListMap<Double, List<Order>> book =
                    (type == Order.Type.BUY) ? buyBook : sellBook;

            book.computeIfAbsent(price, k -> Collections.synchronizedList(new ArrayList<>())).add(order);
            System.out.println("📥 " + trader + " placed " + order);

            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        }
    }

    // Consumer thread: matches best buy and sell orders
    private static void matchOrders() {
        while (true) {
            synchronized (matchLock) {
                if (!buyBook.isEmpty() && !sellBook.isEmpty()) {
                    double highestBuy = buyBook.firstKey();
                    double lowestSell = sellBook.firstKey();

                    if (highestBuy >= lowestSell) {
                        List<Order> buys = buyBook.get(highestBuy);
                        List<Order> sells = sellBook.get(lowestSell);

                        Order buyOrder = buys.remove(0);
                        Order sellOrder = sells.remove(0);

                        int tradedQty = Math.min(buyOrder.quantity, sellOrder.quantity);
                        double tradePrice = (highestBuy + lowestSell) / 2;

                        System.out.println("💸 TRADE: " + tradedQty + " shares @ " + tradePrice +
                                " between " + buyOrder.trader + " and " + sellOrder.trader);

                        if (buys.isEmpty()) buyBook.remove(highestBuy);
                        if (sells.isEmpty()) sellBook.remove(lowestSell);
                    }
                }
            }

            try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        }
    }
}
