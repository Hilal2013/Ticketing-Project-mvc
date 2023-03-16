package com.cydeo.dto;


import com.cydeo.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank
    @Size(max=15,min=2)
    private String firstName;
    @NotBlank
    @Size(max=15,min=12)
    private String lastName;
    @NotBlank
    @Email //it will be email format
    private String userName;
    @NotBlank
    @Pattern (regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}")
    private String passWord;

    private boolean enabled;

    @NotBlank
    @Pattern(regexp = "^\\d{10}$")//should have 10 characters and only numbers
    private String phone;
@NotNull
    private RoleDTO role;
@NotNull
    private Gender gender;

}
