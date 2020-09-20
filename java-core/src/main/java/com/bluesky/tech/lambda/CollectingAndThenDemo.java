package com.bluesky.tech.lambda;

import java.util.List;
        import java.util.Collections;
        import java.util.stream.Collectors;
        import java.util.stream.Stream;
public class CollectingAndThenDemo {
    public static void main(String[] args) {
        List<String> list
                = Stream.of("Demo1", "Demo2").collect(Collectors.collectingAndThen(
                Collectors.toList(),
                Collections::<String> unmodifiableList));
        System.out.println(list);
    }
}
