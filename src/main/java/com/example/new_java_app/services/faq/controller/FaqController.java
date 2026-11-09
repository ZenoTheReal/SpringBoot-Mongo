package com.example.new_java_app.services.faq.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.new_java_app.services.faq.dto.CreateFaqRequest;
import com.example.new_java_app.services.faq.dto.UpdateFaqRequest;
import com.example.new_java_app.services.faq.model.Faq;
import com.example.new_java_app.services.faq.service.FaqService;

import java.util.List;
import java.util.Map;

@Tag(name = "FAQ", description = "FAQ management and CRUD operations")
@RestController
@RequestMapping("/faqs")
public class FaqController {

    private final FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    // GET /faqs (or /faqs?category=billing)
    @Operation(summary = "Get all FAQs", description = "Retrieves a list of all FAQs, optionally filtered by category")
    @GetMapping("/")
    public List<Faq> getAllFaqs(@RequestParam(required = false) String category) {
        return faqService.getAllFaqs(category);
    }

    // GET /faqs/{id}
    @Operation(summary = "Get FAQ by ID", description = "Returns a single FAQ by its MongoDB ID")
    @GetMapping("/{id}")
    public ResponseEntity<Faq> getFaqById(@PathVariable String id) {
        return faqService.getFaqById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /faqs
    @Operation(summary = "Create a new FAQ", description = "Creates and stores a new FAQ in MongoDB")
    @PostMapping
    public ResponseEntity<Faq> createFaq(@RequestBody CreateFaqRequest request) {
        Faq created = faqService.createFaq(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /faqs/{id}
    @Operation(summary = "Update an FAQ", description = "Updates fields of an existing FAQ by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Faq> updateFaq(@PathVariable String id, @RequestBody UpdateFaqRequest request) {
        return faqService.updateFaq(id, request)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /faqs/{id}
    @Operation(summary = "Delete an FAQ", description = "Removes an FAQ from MongoDB by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteFaq(@PathVariable String id) {
        boolean deleted = faqService.deleteFaq(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "FAQ deleted successfully", "id", id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

