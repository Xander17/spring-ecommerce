package ru.geekbrains.adminui.messaging.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleCsv {

    @CsvBindByPosition(position = 0)
    private String name;
}
