package com.terrapix.imageservice.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "image_meta")
public class ImageMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category;
    private String url;
    private String publicId;
    private String format;
    private Long size;

    private Instant uploadedAt = Instant.now();

    public ImageMeta() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getPublicId() { return publicId; }
    public void setPublicId(String publicId) { this.publicId = publicId; }

    public String getFormat() { return format; }
    public void setFormat(String format) { this.format = format; }

    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }

    public Instant getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(Instant uploadedAt) { this.uploadedAt = uploadedAt; }
}
