package com.schoolevents.schoolevents_api.Services;


import com.schoolevents.schoolevents_api.DTO.ImageDTO;
import com.schoolevents.schoolevents_api.exception.ElementNotFoundException;
import com.schoolevents.schoolevents_api.mappers.EventMapper;
import com.schoolevents.schoolevents_api.mappers.ImageMapper;
import com.schoolevents.schoolevents_api.models.*;
import com.schoolevents.schoolevents_api.repositories.EventRepository;
import com.schoolevents.schoolevents_api.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    private  final ImageRepository imageRepository;
    private final  ImageMapper imageMapper;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository, ImageMapper imageMapper, EventMapper eventMapper, EventRepository eventRepository) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
        this.eventMapper = eventMapper;
        this.eventRepository = eventRepository;
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

    public ImageDTO save(ImageDTO image) {
        if (image == null) {
            throw new ElementNotFoundException("No se puede guardar una imagen vacía");
        }

        if (image.getEvent() == null || image.getEvent().getId() == null) {
            throw new ElementNotFoundException("La imagen debe estar asociada a un evento válido");
        }

        Event e = eventRepository.findById(image.getEvent().getId());
        if (e == null) {
            throw new ElementNotFoundException("Evento no encontrado con id: " + image.getEvent().getId());
        }

        Image entity = imageMapper.imageDTOToImage(image);

        entity.setEvent(e);

        Image saved = imageRepository.save(entity);

        return imageMapper.imageToImageDTO(saved);
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

        if (imageDTO.getEvent() == null || imageDTO.getEvent().getId() == null) {
            throw new ElementNotFoundException("La imagen debe estar asociada a un evento válido");
        }
        Event event = eventRepository.findById(imageDTO.getEvent().getId());
        if (event == null) {
            throw new ElementNotFoundException("Evento no encontrado con id: " + imageDTO.getEvent().getId());
        }
        existingImage.setEvent(event);

        Image updatedImage = imageRepository.save(existingImage);
        return imageMapper.imageToImageDTO(updatedImage);
    }



    public void deleteById(Long id){
        if (imageRepository.findById(id) == null) {
            throw new ElementNotFoundException("Imagen no encontrada con el id: "+id);
        } else imageRepository.deleteById(id);
    }
}
