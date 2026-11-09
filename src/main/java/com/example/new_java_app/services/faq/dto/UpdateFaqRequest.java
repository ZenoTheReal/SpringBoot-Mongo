package com.example.new_java_app.services.faq.dto;

public record UpdateFaqRequest(
    String question,
    String answer,
    String category,
    Boolean published
) {}

