package com.example.new_java_app.services.cms.repo;

import com.example.new_java_app.services.cms.model.CmsDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsRepository extends MongoRepository<CmsDocument, String> {

    List<CmsDocument> findByTitleContainingIgnoreCase(String title);
}
