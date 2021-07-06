package com.kodedu.matching;

public class TypeApp {
    public static void main(String[] args) {
        System.out.println(formatterPatternSwitch(1));
        System.out.println(formatterPatternSwitch(1L));
        System.out.println(formatterPatternSwitch(1.0));
        System.out.println(formatterPatternSwitch("Hello"));
        System.out.println(formatterPatternSwitch(1f));
    }

    public static String formatterPatternSwitch(Object o) {
        return switch (o) {
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default        -> o.toString();
        };
    }
}
