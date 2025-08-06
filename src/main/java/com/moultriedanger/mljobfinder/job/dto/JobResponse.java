package com.moultriedanger.mljobfinder.job.dto;

import java.math.BigDecimal;

public class JobResponse {

    private Long jobId;
    private String jobTitle;
    private String jobDescription;
    private String seniorityLevel;
    private BigDecimal maxSalary;
    private String location;
    private String postingUrl;
    private String companyName;

    public JobResponse() {}

    public JobResponse(Long jobId,String jobTitle, String jobDescription, String seniorityLevel, BigDecimal maxSalary, String location, String postingUrl, String companyName) {

        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.seniorityLevel = seniorityLevel;
        this.maxSalary = maxSalary;
        this.location = location;
        this.postingUrl = postingUrl;
        this.companyName = companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Long getJobId() { return jobId; }

    public void setJobId(Long jobId) { this.jobId = jobId; }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getSeniorityLevel() {
        return seniorityLevel;
    }

    public void setSeniorityLevel(String seniorityLevel) {
        this.seniorityLevel = seniorityLevel;
    }

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPostingUrl() {
        return postingUrl;
    }

    public void setPostingUrl(String postingUrl) {
        this.postingUrl = postingUrl;
    }

    public String getCompanyName() { return companyName; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }
}
