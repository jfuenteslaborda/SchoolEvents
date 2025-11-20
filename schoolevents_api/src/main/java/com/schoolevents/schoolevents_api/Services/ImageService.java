package com.schoolevents.schoolevents_api.Services;


import com.schoolevents.schoolevents_api.models.*;
import com.schoolevents.schoolevents_api.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;

    public List<Image> findAll(){
        return imageRepository.findAll();
    }
    
    public Image findById(Long id){
        return imageRepository.findById(id);
    }

    public List<Image> findByEventId(Long event_id){
        return imageRepository.findByEventId(event_id);
    }
    
    public Image save(Image image){
        return imageRepository.save(image);
    }
    
    public Image updateImage(Image image, Long id){
        Image i = imageRepository.findById(id);
        i.setSrc(image.getSrc());
        i.setDescription(image.getDescription());
        i.setEvent(image.getEvent());
        return imageRepository.save(i);
    }
    
    public void deleteById(Long id){
        imageRepository.deleteById(id);
    }
}
