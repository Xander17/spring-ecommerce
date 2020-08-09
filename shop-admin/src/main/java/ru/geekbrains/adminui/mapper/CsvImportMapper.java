package ru.geekbrains.adminui.mapper;

public interface CsvImportMapper<DtoClass, EntityClass> {

    EntityClass fromCsvToEntity(DtoClass productCsv);
}
