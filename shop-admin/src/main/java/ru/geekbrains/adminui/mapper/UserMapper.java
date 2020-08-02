package ru.geekbrains.adminui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.geekbrains.adminui.dto.RoleDto;
import ru.geekbrains.adminui.dto.UserDto;
import ru.geekbrains.adminui.messaging.csv.UserCsv;
import ru.geekbrains.shopdb.model.Role;
import ru.geekbrains.shopdb.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends CsvImportMapper<UserCsv, User> {

    @Mapping(target = "roles", qualifiedByName = "toDtoRole")
    UserDto toDto(User user);

    @Mapping(target = "roles", qualifiedByName = "toEntityRole")
    User toEntity(UserDto userDto);

    @Mapping(target = "users", ignore = true)
    Role toEntityRole(RoleDto roleDto);

    @Mapping(target = "users", ignore = true)
    RoleDto toDtoRole(Role role);

    @Mapping(source = "rolesId", target = "roles", qualifiedByName = "toRoleById")
    User fromCsvToEntity(UserCsv productCsv);

    @Mapping(target = "id")
    Role toRoleById(Integer id);
}
