package com.ggm.cafemanagement.domain.dto;

import com.ggm.cafemanagement.domain.entity.CafeTable;
import com.ggm.cafemanagement.domain.entity.Order;
import com.ggm.cafemanagement.domain.enums.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty(message = "User name is required")
    @Size(min = 3, max = 50, message = "User name size should contain min 3 and max 50 symbol.")
    private String userName;

    @NotBlank(message = "Password is required")
    @Size(min = 4, max = 30, message = "Password size should contain min 4 and max 30 symbol.")
    private String password;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @NotEmpty(message = "First name is required")
    @Size(min = 3, max = 100, message = "First name size should contain  min 3 and max 100 symbol.")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @Size(min = 3, max = 100, message = "Last name size should contain  min 3 and max 100 symbol.")
    private String lastName;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age should be min 18.")
    @Max(value = 45, message = "Age should be max 45.")
    private Integer age;

    private List<CafeTable> tables;

    private List<Order> orders;

}
