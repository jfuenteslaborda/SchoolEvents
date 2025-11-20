package com.schoolevents.schoolevents_api.res;


import com.schoolevents.schoolevents_api.Services.ImageService;
import com.schoolevents.schoolevents_api.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping
    public List<Image> findAll() {
        return imageService.findAll();
    }

    @GetMapping("/{id}")
    public Image findImage(@PathVariable Long id) {
        return imageService.findById(id);
    }

    @GetMapping("/by_event/{id}")
    public List<Image> findImageByEventId(@PathVariable Long id) {
        return  imageService.findByEventId(id);
    }

    @PostMapping
    public Image addImage(@RequestBody Image image) {
        return imageService.save(image);
    }

    @PutMapping("/{id}")
    public Image updateImage(@PathVariable Long id, @RequestBody Image image) {
        return imageService.updateImage(image, id);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        imageService.deleteById(id);
    }
}
