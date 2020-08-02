package ru.geekbrains.adminui.dto.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvRecurse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.adminui.dto.PictureDto;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCsv {

    @CsvBindByName(column = "product_title")
    private String title;

    @CsvBindByName(column = "product_desc")
    private String description;

    @CsvBindByName(column = "product_price")
    private BigDecimal price;

    @CsvRecurse
    private CategoryCsv category;

}
