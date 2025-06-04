package com.moultriedanger.mljobfinder.job.dto;

public class JobResponse {

    private String jobTitle;
    private String jobDescription;
    private String seniorityLevel;
    private String maxSalary;
    private String location;
    private String postingUrl;

    public JobResponse() {}

    public JobResponse(String jobTitle, String jobDescription, String seniorityLevel, String maxSalary, String location, String postingUrl) {

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

    public String getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(String maxSalary) {
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
}
