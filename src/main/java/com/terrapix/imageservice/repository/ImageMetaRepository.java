package com.terrapix.imageservice.repository;

import com.terrapix.imageservice.entity.ImageMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ImageMetaRepository extends JpaRepository<ImageMeta, Long> {
    List<ImageMeta> findByCategory(String category);
}
