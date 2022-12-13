package com.trebnikau.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter

/* Если с классом user связаны другие таблицы, то в классах описывающие таблицы надо имплементировать
 интерфейс Serializable иначе будет ошибка ConversionFailedException
 */
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Please fill the username")
//    @Min(value = 3, message = "Username must be more than 3 symbols")
    private String username;
    @NotBlank(message = "Please fill the password")
//    @Min(value = 6, message = "Password must be more than 6 symbols")
    private String password;
    @NotBlank(message = "Please fill the email")
    @Email(message = "Please fill the correct email")
    private String email;
    @Column(name = "activation_code")
    private String activationCode;
    private boolean active;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private ClientPassport passport;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();


    public boolean isAdmin() {
        return roles.contains(Role.ADMIN);
    }


}



