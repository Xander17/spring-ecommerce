package ru.geekbrains.clientui.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PictureDto implements Serializable {

    private static final long serialVersionUID = 4849414431951160167L;

    private Integer id;

    private String name;

    private String contentType;
}
