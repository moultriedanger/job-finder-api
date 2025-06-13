package com.moultriedanger.mljobfinder.job.mapper;

import com.moultriedanger.mljobfinder.job.Job;
import com.moultriedanger.mljobfinder.job.dto.JobResponse;
import org.springframework.stereotype.Component;

@Component
public class JobResponseMapper {


    public JobResponse toResponseDto(Job job){

        JobResponse jobDTO = new JobResponse();

        jobDTO.setJobId(job.getJobId());
        jobDTO.setJobTitle(job.getJobTitle());
        jobDTO.setJobDescription(job.getJobDescription());
        jobDTO.setSeniorityLevel(job.getSeniorityLevel());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setPostingUrl(job.getPostingUrl());

        String cName = job.getCompany().getCompanyName();
        jobDTO.setCompanyName(cName);

        return jobDTO;
    }


}
