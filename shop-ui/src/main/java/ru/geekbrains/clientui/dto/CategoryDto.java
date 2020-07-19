package ru.geekbrains.clientui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 4200243605939797470L;

    private Integer id;

    private String name;
}
