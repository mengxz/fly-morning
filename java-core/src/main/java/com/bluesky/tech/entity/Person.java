package com.bluesky.tech.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain=true)
public class Person {
    private int id;
    private String name;
    private int age;
}
