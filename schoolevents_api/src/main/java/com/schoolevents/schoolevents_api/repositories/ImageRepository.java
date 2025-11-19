package com.schoolevents.schoolevents_api.repositories;

import com.schoolevents.schoolevents_api.models.Image;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ImageRepository extends Repository<Image, Long> {
    List<Image> findAll();
    Image findById(Long id);
    List<Image> findByEventId(Long event_id);
    Image save(Image image);
    Image update(Image image, Long id);
    void deleteById(Long id);
}
