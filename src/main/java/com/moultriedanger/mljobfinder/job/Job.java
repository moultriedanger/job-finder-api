package com.moultriedanger.mljobfinder.job;

import com.moultriedanger.mljobfinder.company.Company;
import jakarta.persistence.*;

@Entity
public class Job {

    private @Id
    @GeneratedValue Long id;

    private String jobTitle;

    @Column(columnDefinition = "TEXT")
    private String jobDescription;
    private String seniorityLevel;
    private String maxSalary;
    private String location;
    private String postingUrl;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", nullable = false)
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

    public Long getJobId() { return id; }

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

    public String getLocation() { return location; }

    public String getPostingUrl() {
        return postingUrl;
    }

    public Company getCompany() { return company; }

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
