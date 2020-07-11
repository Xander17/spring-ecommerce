package ru.geekbrains.adminui.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Integer id;

    //        @NotBlank(message = "Fill the name field")
    private String name;

    //        @NotBlank(message = "Fill the password field")
    private String password;

    //        @NotBlank(message = "Fill the email field")
//    @Email(message = "Email must be valid (ex. mail@domain.com)")
    private String email;

    private boolean enabled;

    private List<RoleDto> roles;
}
