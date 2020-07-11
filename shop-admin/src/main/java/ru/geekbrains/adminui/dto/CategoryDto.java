package ru.geekbrains.adminui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Integer id;

    private String name;

    private List<ProductDto> products;

    @Override
    public String toString() {
        return id + "#" + name;
    }
}
