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

//    @ManyToOne
//    private Company company;

    public Job(){}

    public Job(String seniorityLevel, String jobDescription, String jobTitle) {
        this.seniorityLevel = seniorityLevel;
        this.jobDescription = jobDescription;
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getSeniorityLevel() {
        return seniorityLevel;
    }

    public void setSeniorityLevel(String seniorityLevel) {
        this.seniorityLevel = seniorityLevel;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

}
