package com.bluesky.teck.lambda;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class OptionalDemo {
    public static void main(String[] args) {
        OptionalInt first = IntStream.range(10, 20)
                .filter(x -> x % 7 == 0)
                .map(x -> x + 10)
                .findFirst();
        if(first.isPresent())
            System.out.println(first.getAsInt());
    }
}
