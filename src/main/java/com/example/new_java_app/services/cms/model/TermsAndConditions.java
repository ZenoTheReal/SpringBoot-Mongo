package com.example.new_java_app.services.cms.model;

public class TermsAndConditions extends CmsDocument {

    private String jurisdiction;
    private int minimumAge;

    public TermsAndConditions() {
        super();
    }

    public TermsAndConditions(String title, String content, int version, String jurisdiction, int minimumAge) {
        super(title, content, version);
        this.jurisdiction = jurisdiction;
        this.minimumAge = minimumAge;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(int minimumAge) {
        this.minimumAge = minimumAge;
    }
}
