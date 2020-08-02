package ru.geekbrains.adminui.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.shopdb.model.Picture;
import ru.geekbrains.shopdb.repo.PictureRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PictureService {

    private final PictureRepository repository;

    public void delete(int id){
        repository.deleteById(id);
    }
}
