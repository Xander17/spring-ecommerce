package ru.geekbrains.adminui.dto.csv;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCsv {

    @CsvBindByName(column = "category_id")
    private Integer id;
}
