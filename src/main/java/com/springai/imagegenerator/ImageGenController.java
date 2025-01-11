package com.springai.imagegenerator;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ImageGenController {
    private final ImageGenService imageGenService;

    public ImageGenController(ImageGenService imageGenService) {
        this.imageGenService = imageGenService;
    }

    @GetMapping("generate-image")
    public void generateImage(HttpServletResponse response, @RequestParam String prompt) throws IOException {
        ImageResponse imageResponse = imageGenService.generateImage(prompt);
        String imageUrl = imageResponse.getResult().getOutput().getUrl();
        response.sendRedirect(imageUrl);
    }

    @GetMapping("generate-multiple-images")
    public List<String> generateMultipleImages(HttpServletResponse response, @RequestParam String prompt,
                @RequestParam(defaultValue = "hd") String quality, @RequestParam(defaultValue = "1") int imageCount,
                @RequestParam(defaultValue = "1024") int height, @RequestParam(defaultValue = "1024") int width) throws IOException {
        ImageResponse imageResponse = imageGenService.generateMultipleImages(prompt, quality, imageCount, height, width);
        List<String> imageUrls = imageResponse.getResults().stream()
            .map(result -> result.getOutput().getUrl())
            .toList();
        return imageUrls;
    }
}
