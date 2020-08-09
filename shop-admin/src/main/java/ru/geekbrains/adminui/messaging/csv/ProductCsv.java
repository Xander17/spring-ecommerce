package ru.geekbrains.adminui.messaging.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCsv {

    @CsvBindByPosition(position = 0)
    private String title;

    @CsvBindByPosition(position = 1)
    private String description;

    @CsvBindByPosition(position = 2)
    private BigDecimal price;

    @CsvBindByPosition(position = 3)
    private Integer category;

}
