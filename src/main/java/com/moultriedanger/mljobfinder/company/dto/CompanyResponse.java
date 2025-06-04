package com.moultriedanger.mljobfinder.company.dto;

public class CompanyResponse {

    private String companyName;
    private String companyDescription;
    private String countryLocated;
    private String companyWebsite;

    public CompanyResponse() {}

    public CompanyResponse(String companyName, String companyDescription, String countryLocated, String companyWebsite) {
        this.companyName = companyName;
        this.companyDescription = companyDescription;
        this.countryLocated = countryLocated;
        this.companyWebsite = companyWebsite;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public String getCountryLocated() {
        return countryLocated;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public void setCountryLocated(String countryLocated) {
        this.countryLocated = countryLocated;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }
}
