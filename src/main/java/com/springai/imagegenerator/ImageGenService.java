package com.springai.imagegenerator;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageGenService {

    private final ImageModel imageModel;

    public ImageGenService(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    public ImageResponse generateImage(String prompt) {
        ImageResponse imageResponse = imageModel.call(
            new ImagePrompt(prompt,
            OpenAiImageOptions.builder()
                .withN(1)
                .withHeight(1024)
                .withWidth(1024).build())
        );

        return imageResponse;
    }

    public ImageResponse generateMultipleImages(String prompt) {
        ImageResponse imageResponse = imageModel.call(
            new ImagePrompt(prompt,
            OpenAiImageOptions.builder()
                .withModel("dall-e-2")
                .withN(3)
                .withHeight(1024)
                .withWidth(1024).build())
        );

        return imageResponse;
    }
    
    
}
