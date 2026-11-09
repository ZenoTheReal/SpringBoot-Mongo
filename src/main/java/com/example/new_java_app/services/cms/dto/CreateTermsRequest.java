package com.example.new_java_app.services.cms.dto;

public record CreateTermsRequest(
    String title,
    String content,
    int version,
    String jurisdiction,
    int minimumAge
) {}
