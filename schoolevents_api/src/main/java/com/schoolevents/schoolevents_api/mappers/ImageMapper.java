package com.schoolevents.schoolevents_api.mappers;

import com.schoolevents.schoolevents_api.DTO.ImageDTO;
import com.schoolevents.schoolevents_api.models.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "event_id", source = "event.id")

    ImageDTO imageToImageDTO(Image image);

    @Mapping(target = "event", ignore = true)

    Image imageDTOToImage(ImageDTO imageDTO);

}
