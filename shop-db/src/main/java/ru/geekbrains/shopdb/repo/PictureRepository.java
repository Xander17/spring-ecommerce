package ru.geekbrains.shopdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.shopdb.model.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
}
