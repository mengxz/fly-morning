package com.bluesky.tech.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private UUID id;
    private String name;
    private int age;
    private boolean gender;
    private String email;
    private boolean employed;
    private BigDecimal salary;
}

