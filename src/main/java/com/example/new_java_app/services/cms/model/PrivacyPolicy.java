package com.example.new_java_app.services.cms.model;

public class PrivacyPolicy extends CmsDocument {

    private String dpoEmail;
    private boolean gdprCompliant;
    private int retentionDays;

    public PrivacyPolicy() {
        super();
    }

    public PrivacyPolicy(String title, String content, int version, String dpoEmail, boolean gdprCompliant, int retentionDays) {
        super(title, content, version);
        this.dpoEmail = dpoEmail;
        this.gdprCompliant = gdprCompliant;
        this.retentionDays = retentionDays;
    }

    public String getDpoEmail() {
        return dpoEmail;
    }

    public void setDpoEmail(String dpoEmail) {
        this.dpoEmail = dpoEmail;
    }

    public boolean isGdprCompliant() {
        return gdprCompliant;
    }

    public void setGdprCompliant(boolean gdprCompliant) {
        this.gdprCompliant = gdprCompliant;
    }

    public int getRetentionDays() {
        return retentionDays;
    }

    public void setRetentionDays(int retentionDays) {
        this.retentionDays = retentionDays;
    }
}
