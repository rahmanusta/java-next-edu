package com.kodedu.matching;

import java.math.BigDecimal;

public class GuardedApp {

    public static void main(String[] args) {
        enum Side {BUY, SELL}
        record Order(Side side, BigDecimal price) {
        }

        Order order = new Order(Side.BUY, BigDecimal.TEN);

        switch (order) {
            case Order o && order.side() == Side.BUY -> {
                System.out.println("Buy order: " + o);
            }
            case Order o -> System.out.println("Sell order: " + o);
        }

    }
}
