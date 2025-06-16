package com.moultriedanger.mljobfinder.company;

public class companyBuilder {

    private String companyName = "Default name";
    private String companyDescription = "This company has a cool description";
    private String countryLocated = "USA";
    private String companyWebsite = "http://google.com";

    public companyBuilder withCompanyName(String companyName){
        this.companyName = companyName;
        return this;
    }

    public companyBuilder withCompanyDescription(String companyDescription){
        this.companyDescription = companyDescription;
        return this;
    }

    public companyBuilder countryLocated(String countryLocated){
        this.countryLocated = countryLocated;
        return this;
    }

    public companyBuilder companyWebsite(String companyWebsite){
        this.companyWebsite = companyWebsite;
        return this;
    }

    public Company build(){

        return new Company(companyName, companyDescription, countryLocated, companyWebsite);
    }
}
