package ru.geekbrains.adminui.formatter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import ru.geekbrains.adminui.dto.RoleDto;

import java.text.ParseException;
import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@Component
public class RoleFormatter implements Formatter<RoleDto> {

    @Override
    public RoleDto parse(String s, Locale locale) throws ParseException {
        String[] str = s.split("#", 2);
        return new RoleDto(Integer.parseInt(str[0]), str[1], null);
    }

    @Override
    public String print(RoleDto role, Locale locale) {
        return role.toString();
    }
}
