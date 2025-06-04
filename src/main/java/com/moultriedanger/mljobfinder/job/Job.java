package com.moultriedanger.mljobfinder.job;

import com.moultriedanger.mljobfinder.company.Company;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Job {

    private @Id
    @GeneratedValue Long id;

    public String jobTitle;

    public String jobDescription;

    public String seniorityLevel;

    public String maxSalary;

//    public String payPeriod;

    public String location;

    public String postingUrl;

    @ManyToOne
    private Company company;

    public Job(){}

    public Job(String jobTitle, String jobDescription, String seniorityLevel, String maxSalary, String location, String postingUrl) {
        System.out.println("Constructor values -> " + jobTitle + ", " + maxSalary + ", " + location + ", " + postingUrl);
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.seniorityLevel = seniorityLevel;
        this.maxSalary = maxSalary;
        this.location = location;
        this.postingUrl = postingUrl;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getSeniorityLevel() {
        return seniorityLevel;
    }

    public String getMaxSalary() {
        return maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public String getPostingUrl() {
        return postingUrl;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setSeniorityLevel(String seniorityLevel) {
        this.seniorityLevel = seniorityLevel;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setMaxSalary(String maxSalary) {
        this.maxSalary = maxSalary;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPostingUrl(String postingUrl) {
        this.postingUrl = postingUrl;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
