package com.example.new_java_app.services.cms.dto;

public record CreatePrivacyPolicyRequest(
    String title,
    String content,
    int version,
    String dpoEmail,
    boolean gdprCompliant,
    int retentionDays
) {}
