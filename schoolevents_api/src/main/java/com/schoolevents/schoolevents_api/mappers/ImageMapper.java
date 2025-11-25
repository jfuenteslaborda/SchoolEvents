package com.schoolevents.schoolevents_api.mappers;

import com.schoolevents.schoolevents_api.DTO.ImageDTO;
import com.schoolevents.schoolevents_api.models.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ImageMapper {

    ImageDTO imageDTOToImageDTO(Image image);

    Image imageDTOToImage(ImageDTO imageDTO);

}
