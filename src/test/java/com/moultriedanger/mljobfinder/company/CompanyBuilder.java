package com.moultriedanger.mljobfinder.company;

public class CompanyBuilder {

    private String companyName = "Default name";
    private String companyDescription = "This company has a cool description";
    private String countryLocated = "USA";
    private String companyWebsite = "http://google.com";

    public CompanyBuilder withCompanyName(String companyName){
        this.companyName = companyName;
        return this;
    }

    public CompanyBuilder withCompanyDescription(String companyDescription){
        this.companyDescription = companyDescription;
        return this;
    }

    public CompanyBuilder countryLocated(String countryLocated){
        this.countryLocated = countryLocated;
        return this;
    }

    public CompanyBuilder companyWebsite(String companyWebsite){
        this.companyWebsite = companyWebsite;
        return this;
    }

    public Company build(){

        return new Company(companyName, companyDescription, countryLocated, companyWebsite);
    }
}
