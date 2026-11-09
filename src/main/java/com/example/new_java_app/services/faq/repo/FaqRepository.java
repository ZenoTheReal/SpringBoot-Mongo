package com.example.new_java_app.services.faq.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.new_java_app.services.faq.model.Faq;

import java.util.List;

@Repository
public interface FaqRepository extends MongoRepository<Faq, String> {

    List<Faq> findByCategory(String category);

    List<Faq> findByPublished(boolean published);
}
