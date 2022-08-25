package com.trebnikau.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String producer;
    private String model;
    @Column(name = "release_date")
    private String releaseDate;
    @Column(name = "price_per_day")
    private Double pricePerDay;
    @Column(name = "employment_status")
    private boolean employmentStatus;
    @Column(name = "damage_status")
    private String damageStatus;


}
