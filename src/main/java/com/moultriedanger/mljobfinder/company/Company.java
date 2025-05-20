package com.moultriedanger.mljobfinder.company;

import com.moultriedanger.mljobfinder.job.Job;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {

    private @Id
    @GeneratedValue Long id;

    private String companyName;
    private String companyDescription;
    private String companyWebsite;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Job> jobs = new ArrayList<>();

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

    //add job
    public void addJob(Job job){
        this.jobs.add(job);
        job.setCompany(this);
    }

    public List<Job> getJobs(){
        return jobs;
    }
}
