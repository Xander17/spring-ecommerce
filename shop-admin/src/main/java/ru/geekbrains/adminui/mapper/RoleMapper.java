package ru.geekbrains.adminui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.geekbrains.adminui.dto.RoleDto;
import ru.geekbrains.adminui.dto.UserDto;
import ru.geekbrains.adminui.messaging.csv.RoleCsv;
import ru.geekbrains.shopdb.model.Role;
import ru.geekbrains.shopdb.model.User;

@Mapper(componentModel = "spring")
public interface RoleMapper extends CsvImportMapper<RoleCsv, Role> {

    @Mapping(target = "users", qualifiedByName = "toDtoUser")
    RoleDto toDto(Role role);

    @Mapping(target = "users", qualifiedByName = "toEntityUser")
    Role toEntity(RoleDto roleDto);

    @Mapping(target = "roles", ignore = true)
    User toEntityUser(UserDto userDto);

    @Mapping(target = "roles", ignore = true)
    UserDto toDtoUser(User user);

    Role fromCsvToEntity(RoleCsv productCsv);
}
