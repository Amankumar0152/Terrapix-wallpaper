package com.terrapix.imageservice.dto;

public class ImageUploadResponse {

    private String message;
    private String url;

    public ImageUploadResponse(String message, String url) {
        this.message = message;
        this.url = url;
    }

    public String getMessage() { return message; }

    public String getUrl() { return url; }
}
