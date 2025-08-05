package com.moultriedanger.mljobfinder.job;

import com.moultriedanger.mljobfinder.company.Company;

public class JobBuilder {
    private String jobTitle = "Default Job Title";
    private String jobDescription = "Default Description";
    private String seniorityLevel = "Entry";
    private Long maxSalary = 50000L;
    private String location = "Remote";
    private String postingUrl = "https://example.com";
    private Company company;

    public JobBuilder withJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
        return this;
    }

    public JobBuilder withJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
        return this;
    }

    public JobBuilder withSeniorityLevel(String seniorityLevel) {
        this.seniorityLevel = seniorityLevel;
        return this;
    }

    public JobBuilder withMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
        return this;
    }

    public JobBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public JobBuilder withPostingUrl(String postingUrl) {
        this.postingUrl = postingUrl;
        return this;
    }

    public JobBuilder withCompany(Company company) {
        this.company = company;
        return this;
    }

    public Job build() {
        Job job = new Job(jobTitle, jobDescription, seniorityLevel, maxSalary, location, postingUrl);
        job.setCompany(company);
        return job;
    }
}
