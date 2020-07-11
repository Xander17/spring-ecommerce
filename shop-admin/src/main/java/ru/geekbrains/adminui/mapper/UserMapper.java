package ru.geekbrains.adminui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.geekbrains.adminui.dto.RoleDto;
import ru.geekbrains.adminui.dto.UserDto;
import ru.geekbrains.shopdb.model.Role;
import ru.geekbrains.shopdb.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", qualifiedByName = "toDtoRole")
    UserDto toDto(User user);

    @Mapping(target = "roles", qualifiedByName = "toEntityRole")
    User toEntity(UserDto userDto);

    @Mapping(target = "users", ignore = true)
    Role toEntityRole(RoleDto roleDto);

    @Mapping(target = "users", ignore = true)
    RoleDto toDtoRole(Role role);
}
