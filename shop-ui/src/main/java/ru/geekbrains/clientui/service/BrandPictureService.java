package ru.geekbrains.clientui.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.shopdb.model.BrandPicture;
import ru.geekbrains.shopdb.repo.BrandPictureRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandPictureService {

    private final BrandPictureRepository repository;

    public Optional<BrandPicture> findById(int id) {
        return repository.findById(id);
    }
}
