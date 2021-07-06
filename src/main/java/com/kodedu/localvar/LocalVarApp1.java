package com.kodedu.localvar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LocalVarApp1 {

//    var numbers3 = new ArrayList<>();

    public static void main(String[] args) {
        someMethod();
    }

    private static void someMethod() {
        List<Integer> numbers1 = new ArrayList<>();
        Stream<Integer> stream1 = numbers1.stream();

        var numbers2 = new ArrayList<Integer>();
        var stream2 = numbers2.stream();

    }
}
