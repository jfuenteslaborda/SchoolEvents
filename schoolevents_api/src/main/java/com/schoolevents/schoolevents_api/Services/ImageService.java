package com.schoolevents.schoolevents_api.Services;


import com.schoolevents.schoolevents_api.DTO.ImageDTO;
import com.schoolevents.schoolevents_api.exception.ElementNotFoundException;
import com.schoolevents.schoolevents_api.mappers.EventMapper;
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
    private final EventMapper eventMapper;

    @Autowired
    public ImageService(ImageRepository imageRepository, ImageMapper imageMapper, EventMapper eventMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
        this.eventMapper = eventMapper;
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
        if (images.isEmpty()){
            throw new ElementNotFoundException("No hay imagenes registradas para el evento con el id: "+event_id);
        } else return imagesDTO;
    }
    
    public ImageDTO save(ImageDTO image){
        if (image == null) {
            throw new ElementNotFoundException("No se puede guardar una imagen vacia");
        } else {
            Image i = imageRepository.save(imageMapper.imageDTOToImage(image));
            return imageMapper.imageToImageDTO(i);
        }
    }

    public ImageDTO updateImage(ImageDTO imageDTO, Long id) {
        if (imageDTO == null) {
            throw new ElementNotFoundException("No se puede actualizar una imagen vacía");
        }

        Image existingImage = imageRepository.findById(id);
        if (existingImage == null) {
            throw new ElementNotFoundException("Imagen no encontrada con el id: "+id);
        }

        existingImage.setSrc(imageDTO.getSrc());
        existingImage.setDescription(imageDTO.getDescription());
        existingImage.setEvent(eventMapper.eventDTOToEvent(imageDTO.getEvent()));

        Image updatedImage = imageRepository.save(existingImage);

        return imageMapper.imageToImageDTO(updatedImage);
    }


    public void deleteById(Long id){
        if (imageRepository.findById(id) == null) {
            throw new ElementNotFoundException("Imagen no encontrada con el id: "+id);
        } else imageRepository.deleteById(id);
    }
}
