package com.ggm.cafemanagement.domain.entity;

import com.ggm.cafemanagement.domain.enums.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotEmpty(message = "User name is required")
    @Size(min = 3, max = 50, message = "User name size should contain min 3 and max 50 symbol.")
    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @NotEmpty(message = "Password is required")
    @Size(min = 4, max = 30, message = "Password size should contain min 4 and max 30 symbol.")
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    @NotEmpty(message = "First name is required")
    @Size(min = 3, max = 100, message = "First name size should contain  min 3 and max 100 symbol.")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(min = 3, max = 100, message = "Last name size should contain  min 3 and max 100 symbol.")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Age is required")
    @Size(min = 18, max = 45, message = "Age should be between in 18 and 45 range.")
    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "waiter", cascade = CascadeType.ALL)
    private List<CafeTable> tables;

    @OneToMany(mappedBy = "waiter", cascade = CascadeType.ALL)
    private List<Order> orders;

}
