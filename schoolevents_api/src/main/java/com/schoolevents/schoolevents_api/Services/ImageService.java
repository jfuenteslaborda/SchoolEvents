package com.schoolevents.schoolevents_api.Services;


import com.schoolevents.schoolevents_api.DTO.ImageDTO;
import com.schoolevents.schoolevents_api.exception.ElementNotFoundException;
import com.schoolevents.schoolevents_api.mappers.ImageMapper;
import com.schoolevents.schoolevents_api.models.*;
import com.schoolevents.schoolevents_api.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private  final ImageRepository imageRepository;
    private final  ImageMapper imageMapper;

    @Autowired
    public ImageService(ImageRepository imageRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    public List<ImageDTO> findAll(){
        List<Image> images = imageRepository.findAll();
        List<ImageDTO> imagesDTO = new java.util.ArrayList<>(List.of());
        for (Image i : images) {
            imagesDTO.add(imageMapper.imageToImageDTO(i));
        }
        if (images.isEmpty()){
            throw new ElementNotFoundException("No hay imagenes registradas");
        } else return imagesDTO;
    }
    
    public ImageDTO findById(Long id){
        Image image = imageRepository.findById(id);
        if (image == null) {
            throw new ElementNotFoundException("Imagen no encontrada con el id: "+id);
        } else return imageMapper.imageToImageDTO(image);
    }

    public List<ImageDTO> findByEventId(Long event_id){
        List<Image> images = imageRepository.findByEventId(event_id);
        List<ImageDTO> imagesDTO = new java.util.ArrayList<>(List.of());
        for (Image i : images) {
            imagesDTO.add(imageMapper.imageToImageDTO(i));
        }
        return imagesDTO;
    }
    
    public ImageDTO save(Image image){
        Image i = imageRepository.save(image);
        return imageMapper.imageToImageDTO(i);
    }
    
    public ImageDTO updateImage(Image image, Long id){
        Image i = imageRepository.findById(id);
        i.setSrc(image.getSrc());
        i.setDescription(image.getDescription());
        i.setEvent(image.getEvent());
        Image image0 = imageRepository.save(i);
        return imageMapper.imageToImageDTO(image0);
    }
    
    public void deleteById(Long id){
        imageRepository.deleteById(id);
    }
}
