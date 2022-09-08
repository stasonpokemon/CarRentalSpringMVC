package com.trebnikau.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    private double price;
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @Column(name = "order_date")
    private Timestamp orderDate;
    @NotNull(message = "Please fill the rental period")
    @Column(name = "rental_period")
    private Integer rentalPeriod;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "refund_id")
    private Refund refund;

}