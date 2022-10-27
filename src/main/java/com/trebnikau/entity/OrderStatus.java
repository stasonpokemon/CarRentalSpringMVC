package com.trebnikau.entity;

public enum OrderStatus {
    UNDER_CONSIDERATION("НА РАССМОТРЕНИИ"),
    REFUSAL("ОТКАЗАНО"),
    CONFIRMED("ПРИНЯТО");

    String abbreviation;

    private OrderStatus(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation(){
        return this.abbreviation;
    }
}
