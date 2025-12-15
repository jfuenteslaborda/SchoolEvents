package com.schoolevents.schoolevents_api.res;


import com.schoolevents.schoolevents_api.DTO.ImageDTO;
import com.schoolevents.schoolevents_api.Services.ImageService;
import com.schoolevents.schoolevents_api.models.Image;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/all")
    public List<ImageDTO> findAll() {
        return imageService.findAll();
    }

    @GetMapping("/by_id/{id}")
    public ImageDTO findImage(@PathVariable Long id) {
        return imageService.findById(id);
    }

    @GetMapping("/by_event/{id}")
    public List<ImageDTO> findImageByEventId(@PathVariable Long id) {
        return  imageService.findByEventId(id);
    }

    @PostMapping("/create")
    public ImageDTO addImage(@Valid @RequestBody ImageDTO image) {
        return imageService.save(image);
    }

    @PutMapping("/update/{id}")
    public ImageDTO updateImage(@PathVariable Long id, @Valid @RequestBody ImageDTO image) {
        return imageService.updateImage(image, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteImage(@PathVariable Long id) {
        imageService.deleteById(id);
    }
}
