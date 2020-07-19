package ru.geekbrains.shopdb.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.shopdb.model.BrandPicture;

@Repository
public interface BrandPictureRepository extends JpaRepository<BrandPicture, Integer> {
}
