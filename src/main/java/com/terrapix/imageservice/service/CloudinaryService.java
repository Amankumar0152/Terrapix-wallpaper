package com.terrapix.imageservice.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public Map uploadImage(MultipartFile file, String folder) throws IOException {

        Map uploadParams = ObjectUtils.asMap(
                "folder", folder,
                "resource_type", "image",
                "use_filename", true,
                "unique_filename", false,
                "overwrite", true
        );

        return cloudinary.uploader().upload(file.getBytes(), uploadParams);
    }

    public Map deleteImage(String publicId) throws Exception {
        return cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
    }
}
