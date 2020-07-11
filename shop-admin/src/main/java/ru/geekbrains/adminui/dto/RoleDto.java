package ru.geekbrains.adminui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private Integer id;

    private String name;

    private List<UserDto> users;

    @Override
    public String toString() {
        return id + "#" + name;
    }
}
