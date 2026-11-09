package com.example.new_java_app.services.cms.service;

import com.example.new_java_app.services.cms.dto.CreatePrivacyPolicyRequest;
import com.example.new_java_app.services.cms.dto.CreateTermsRequest;
import com.example.new_java_app.services.cms.model.CmsDocument;
import com.example.new_java_app.services.cms.model.PrivacyPolicy;
import com.example.new_java_app.services.cms.model.TermsAndConditions;
import com.example.new_java_app.services.cms.repo.CmsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CmsService {

    private final CmsRepository cmsRepository;

    public CmsService(CmsRepository cmsRepository) {
        this.cmsRepository = cmsRepository;
    }

    // Returns ALL CMS documents (both Terms and Policies, automatically typed via _class!)
    public List<CmsDocument> getAllDocuments() {
        return cmsRepository.findAll();
    }

    public Optional<CmsDocument> getDocumentById(String id) {
        return cmsRepository.findById(id);
    }

    // Saves a TermsAndConditions document
    public TermsAndConditions createTerms(CreateTermsRequest req) {
        TermsAndConditions terms = new TermsAndConditions(
            req.title(),
            req.content(),
            req.version(),
            req.jurisdiction(),
            req.minimumAge()
        );
        return cmsRepository.save(terms);
    }

    // Saves a PrivacyPolicy document
    public PrivacyPolicy createPrivacyPolicy(CreatePrivacyPolicyRequest req) {
        PrivacyPolicy policy = new PrivacyPolicy(
            req.title(),
            req.content(),
            req.version(),
            req.dpoEmail(),
            req.gdprCompliant(),
            req.retentionDays()
        );
        return cmsRepository.save(policy);
    }

    public boolean deleteDocument(String id) {
        if (cmsRepository.existsById(id)) {
            cmsRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
