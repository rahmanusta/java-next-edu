package com.kodedu.matching;

public class NullApp {

    /*
          Switch can match with null
     */
    public static void main(String[] args) {

        Object o = null;
        switch (o) {
            case null -> System.out.println("Received null");
            default -> System.out.println("Default case");
        }

    }
}
