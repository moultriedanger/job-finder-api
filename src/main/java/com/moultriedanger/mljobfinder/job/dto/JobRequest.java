package com.moultriedanger.mljobfinder.job.dto;

import com.moultriedanger.mljobfinder.company.Company;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public class JobRequest {

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Job description is required")
    private String jobDescription;

    @NotBlank(message = "Job seniority is required")
    private String seniorityLevel;

    @NotBlank(message = "Job salary is required")
    private BigDecimal maxSalary;

    @NotBlank(message = "Job location is required")
    private String location;

    @NotBlank(message = "Job url is required")
    private String postingUrl;

    @NotNull(message = "Company ID is required")
    private Long companyId;

    public JobRequest() {}

    public JobRequest(String jobTitle, String jobDescription, String seniorityLevel, BigDecimal maxSalary, String location, String postingUrl, Long companyId) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.seniorityLevel = seniorityLevel;
        this.maxSalary = maxSalary;
        this.location = location;
        this.postingUrl = postingUrl;
        this.companyId = companyId;
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

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public String getPostingUrl() {
        return postingUrl;
    }

    public Long getCompanyId(){ return companyId; }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setSeniorityLevel(String seniorityLevel) {
        this.seniorityLevel = seniorityLevel;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPostingUrl(String postingUrl) {
        this.postingUrl = postingUrl;
    }

    public void setCompanyId(Long companyId){ this.companyId = companyId; }
}
