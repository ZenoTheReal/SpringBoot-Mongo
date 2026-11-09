package com.example.new_java_app.services.cms.controller;

import com.example.new_java_app.services.cms.dto.CreatePrivacyPolicyRequest;
import com.example.new_java_app.services.cms.dto.CreateTermsRequest;
import com.example.new_java_app.services.cms.model.CmsDocument;
import com.example.new_java_app.services.cms.model.PrivacyPolicy;
import com.example.new_java_app.services.cms.model.TermsAndConditions;
import com.example.new_java_app.services.cms.service.CmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name = "CMS (Inheritance Demo)", description = "CMS Terms & Privacy Policy with MongoDB Polymorphic Inheritance")
@RestController
@RequestMapping("/cms")
public class CmsController {

    private final CmsService cmsService;

    public CmsController(CmsService cmsService) {
        this.cmsService = cmsService;
    }

    @Operation(summary = "Get All CMS Documents", description = "Fetches all documents from the single cms_documents collection. Spring automatically uses _class to serialize each into Terms or Policy!")
    @GetMapping
    public List<CmsDocument> getAllDocuments() {
        return cmsService.getAllDocuments();
    }

    @Operation(summary = "Get Document by ID")
    @GetMapping("/{id}")
    public ResponseEntity<CmsDocument> getDocumentById(@PathVariable String id) {
        return cmsService.getDocumentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create Terms and Conditions", description = "Saves into cms_documents collection with _class = TermsAndConditions")
    @PostMapping("/terms")
    public ResponseEntity<TermsAndConditions> createTerms(@RequestBody CreateTermsRequest request) {
        TermsAndConditions created = cmsService.createTerms(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Create Privacy Policy", description = "Saves into cms_documents collection with _class = PrivacyPolicy")
    @PostMapping("/privacy-policy")
    public ResponseEntity<PrivacyPolicy> createPrivacyPolicy(@RequestBody CreatePrivacyPolicyRequest request) {
        PrivacyPolicy created = cmsService.createPrivacyPolicy(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Delete CMS Document")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteDocument(@PathVariable String id) {
        boolean deleted = cmsService.deleteDocument(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Document deleted successfully", "id", id));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
