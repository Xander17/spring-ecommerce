package ru.geekbrains.adminui.services;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.adminui.messaging.enums.CsvImportType;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImportCsvService {

    @Value("${app.import.csv-product-dir}")
    private String importDirPath;

    private Path importDirectory;

    @PostConstruct
    public void init() throws IOException {
        this.importDirectory = Paths.get(importDirPath);
        Files.createDirectories(importDirectory);
    }

    public void uploadFile(MultipartFile file, CsvImportType type) throws IOException {
        String fileName = file.getOriginalFilename();
        if (Strings.isBlank(fileName) || !fileName.endsWith(".csv")) return;
        file.transferTo(importDirectory.resolve(String.format("%s_%s.csv", type.getFilePrefix(), UUID.randomUUID().toString())));
    }

}
