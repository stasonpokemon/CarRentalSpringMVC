package com.trebnikau.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "refunds")
@Getter
@Setter
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(mappedBy = "refund", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Order order;
    @Column(name = "damage_status")
    private boolean damageStatus;
    @Column(name = "damage_description")
    private String damageDescription;
    private double price;

}
