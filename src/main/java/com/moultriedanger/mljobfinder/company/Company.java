package com.moultriedanger.mljobfinder.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moultriedanger.mljobfinder.job.Job;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    @Lob
    private String companyDescription;
    private String countryLocated;
    private String companyWebsite;


    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    @JsonIgnore
    private List<Job> jobs = new ArrayList<>();

    public Company(){}

    public Company(String companyName, String companyDescription, String companyWebsite, String countryLocated){
        this.companyName = companyName;
        this.companyDescription = companyDescription;
        this.companyWebsite = companyWebsite;
        this.countryLocated = countryLocated;
    }

    public Long getCompanyId() { return id; }

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public String getCountryLocated() { return countryLocated; }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCompanyDescription(String companyDescription){
        this.companyDescription = companyDescription;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public void setCountryLocated(String countryLocated) { this.countryLocated = countryLocated; }

    @Override
    public String toString(){
        return "Name: " + companyName + " Description: " + companyDescription + " Website: " + companyWebsite + " Located: " + countryLocated;
    }

    //add job
    public void addJob(Job job){
        this.jobs.add(job);
        job.setCompany(this);
    }

    public List<Job> getJobs(){
        return jobs;
    }
}
