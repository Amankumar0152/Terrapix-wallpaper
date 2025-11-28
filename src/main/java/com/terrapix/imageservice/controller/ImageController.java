package com.terrapix.imageservice.controller;

import com.terrapix.imageservice.dto.ImageUploadResponse;
import com.terrapix.imageservice.entity.ImageMeta;
import com.terrapix.imageservice.repository.ImageMetaRepository;
import com.terrapix.imageservice.service.CloudinaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final CloudinaryService cloudinaryService;
    private final ImageMetaRepository imageMetaRepository;

    public ImageController(CloudinaryService cloudinaryService,
                           ImageMetaRepository imageMetaRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageMetaRepository = imageMetaRepository;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam(value = "category", required = false) String category
    ) throws Exception {

        // Upload to Cloudinary
        Map uploadResult = cloudinaryService.uploadImage(file, "terrapix");

        // Save metadata in DB
        ImageMeta meta = new ImageMeta();
        meta.setTitle(title);
        meta.setCategory(category);
        meta.setUrl((String) uploadResult.get("secure_url"));
        meta.setPublicId((String) uploadResult.get("public_id"));
        meta.setFormat((String) uploadResult.get("format"));
        meta.setSize(((Number) uploadResult.get("bytes")).longValue());

        imageMetaRepository.save(meta);

        // Return response
        return ResponseEntity.ok(
                new ImageUploadResponse("Uploaded Successfully", meta.getUrl())
        );
    }

    @GetMapping
    public ResponseEntity<?> getAllImages() {
        return ResponseEntity.ok(imageMetaRepository.findAll());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(imageMetaRepository.findByCategory(category));
    }
}
