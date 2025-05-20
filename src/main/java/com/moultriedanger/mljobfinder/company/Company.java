package com.moultriedanger.mljobfinder.company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Company {

    private @Id
    @GeneratedValue Long id;

    private String companyName;
    private String companyDescription;
    private String companyWebsite;

    public Company(){}

    public Company(String companyName, String companyDescription, String companyWebsite){
        this.companyName = companyName;
        this.companyDescription = companyDescription;
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

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyDescription(String companyDescription){
        this.companyDescription = companyDescription;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    @Override
    public String toString(){
        return "Name: " + companyName + " Description: " + companyDescription + " Website: " + companyWebsite;
    }
}
