package com.moultriedanger.mljobfinder.job.mapper;

import com.moultriedanger.mljobfinder.company.Company;
import com.moultriedanger.mljobfinder.job.Job;
import com.moultriedanger.mljobfinder.job.dto.JobRequest;
import org.springframework.stereotype.Component;

@Component
public class JobRequestMapper {

    public Job toJobEntity(JobRequest jobRequest, Company company) {

        Job job = new Job();

        job.setJobTitle(jobRequest.getJobTitle());
        job.setJobDescription(jobRequest.getJobDescription());
        job.setSeniorityLevel(jobRequest.getSeniorityLevel());
        job.setMaxSalary(jobRequest.getMaxSalary());
        job.setLocation(jobRequest.getLocation());
        job.setPostingUrl(jobRequest.getPostingUrl());
        job.setCompany(company);

        return job;
    }

}
