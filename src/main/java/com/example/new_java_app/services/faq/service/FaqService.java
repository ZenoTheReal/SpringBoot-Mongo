package com.example.new_java_app.services.faq.service;

import org.springframework.stereotype.Service;

import com.example.new_java_app.services.faq.dto.CreateFaqRequest;
import com.example.new_java_app.services.faq.dto.UpdateFaqRequest;
import com.example.new_java_app.services.faq.model.Faq;
import com.example.new_java_app.services.faq.repo.FaqRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FaqService {

    private final FaqRepository faqRepository;

    public FaqService(FaqRepository faqRepository) {
        this.faqRepository = faqRepository;
    }

    public List<Faq> getAllFaqs(String category) {
        if (category != null && !category.isBlank()) {
            return faqRepository.findByCategory(category);
        }
        return faqRepository.findAll();
    }

    public Optional<Faq> getFaqById(String id) {
        return faqRepository.findById(id);
    }

    public Faq createFaq(CreateFaqRequest request) {
        Faq faq = new Faq(
            request.question(),
            request.answer(),
            request.category() != null ? request.category() : "general",
            request.published() != null ? request.published() : true
        );
        return faqRepository.save(faq);
    }

    public Optional<Faq> updateFaq(String id, UpdateFaqRequest request) {
        return faqRepository.findById(id).map(existingFaq -> {
            if (request.question() != null) existingFaq.setQuestion(request.question());
            if (request.answer() != null) existingFaq.setAnswer(request.answer());
            if (request.category() != null) existingFaq.setCategory(request.category());
            if (request.published() != null) existingFaq.setPublished(request.published());
            return faqRepository.save(existingFaq);
        });
    }

    public boolean deleteFaq(String id) {
        if (faqRepository.existsById(id)) {
            faqRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
