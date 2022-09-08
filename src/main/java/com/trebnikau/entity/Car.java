package com.trebnikau.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cars")
@Getter
@Setter
@ToString
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please fill the producer")
    @Length(max = 2048, message = "Producer too long. Max length is 2048")
    private String producer;
    @NotBlank(message = "Please fill the model")
    @Length(max = 2048, message = "Model too long. Max length is 2048")
    private String model;
    @NotBlank(message = "Please fill the release date")
    @Length(max = 2048, message = "Release date too long. Max length is 2048")
    @Column(name = "release_date")
    private String releaseDate;
    @NotNull(message = "Please fill the price per day")
    @Min(value = 0, message = "Price per day can't be less than 0")
    @Column(name = "price_per_day")
    private Double pricePerDay;
    @NotNull(message = "Employment status can't be null")
    @Column(name = "employment_status")
    private boolean employmentStatus;
    @NotBlank(message = "Please fill the damage status")
    @Column(name = "damage_status")
    private String damageStatus;
    @NotBlank(message = "Please fill the image link")
    @Column(name = "img_link")
    private String imageLink;

}
