package com.trebnikau.entity;

public enum OrderStatus {
    UNDER_CONSIDERATION("НА РАССМОТРЕНИИ"),
    REFUSAL("ОТКАЗАНО"),
    CONFIRMED("ПРИНЯТО");

    String abbreviation;

    OrderStatus(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
