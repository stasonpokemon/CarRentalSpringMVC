package com.trebnikau.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "passports")
@Getter
@Setter
public class ClientPassport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please fill the name")
    private String name;
    @NotBlank(message = "Please fill the surname")
    private String surname;
    @NotBlank(message = "Please fill the patronymic")
    private String patronymic;
    @NotBlank(message = "Please fill the birthday")
    private String birthday;
    @NotBlank(message = "Please fill the address")
    private String address;


}
