package ru.geekbrains.clientui.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.shopdb.model.Picture;
import ru.geekbrains.shopdb.repo.PictureRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final PictureRepository repository;

    public Optional<Picture> findById(int id) {
        return repository.findById(id);
    }
}
