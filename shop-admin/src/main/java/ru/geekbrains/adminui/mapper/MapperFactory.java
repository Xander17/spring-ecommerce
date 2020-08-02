package ru.geekbrains.adminui.mapper;

import org.springframework.stereotype.Component;
import ru.geekbrains.adminui.messaging.enums.CsvImportType;

import java.util.HashMap;
import java.util.Map;

@Component
public class MapperFactory {

    private final Map<CsvImportType, CsvImportMapper<?, ?>> mapperMap = new HashMap<>();

    public MapperFactory(ProductMapper productMapper,
                         CategoryMapper categoryMapper,
                         BrandMapper brandMapper,
                         RoleMapper roleMapper,
                         UserMapper userMapper) {
        mapperMap.put(CsvImportType.PRODUCT, productMapper);
        mapperMap.put(CsvImportType.CATEGORY, categoryMapper);
        mapperMap.put(CsvImportType.BRAND, brandMapper);
        mapperMap.put(CsvImportType.ROLE, roleMapper);
        mapperMap.put(CsvImportType.USER, userMapper);
    }

    public <S, D> CsvImportMapper<S, D> getMapper(CsvImportType type, Class<S> srcClass, Class<D> dstClass) {
        return (CsvImportMapper<S, D>) mapperMap.get(type);
    }
}
