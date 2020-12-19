package com.ggm.cafemanagement.domain.entity;

import com.ggm.cafemanagement.domain.enums.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "waiter", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<CafeTable> tables;

    @OneToMany(mappedBy = "waiter", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Order> orders;

}
