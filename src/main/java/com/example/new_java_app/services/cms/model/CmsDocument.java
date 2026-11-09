package com.example.new_java_app.services.cms.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(collection = "cms_documents")
public abstract class CmsDocument {

    @Id
    private String id;
    private String title;
    private String content;
    private int version;
    private Instant createdAt = Instant.now();

    public CmsDocument() {
    }

    public CmsDocument(String title, String content, int version) {
        this.title = title;
        this.content = content;
        this.version = version;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
