package com.moultriedanger.mljobfinder.job.mapper;

import com.moultriedanger.mljobfinder.job.Job;
import com.moultriedanger.mljobfinder.job.dto.JobResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JobResponseMapper {

    /*
    PARAM: Job entity
    Return: JobResponse
     */
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

    /*
    PARAM: Job entity list
    Return: JobResponse list
    */
    public List<JobResponse> toResponseDtoList(List<Job> jobs){

        List<JobResponse> jobResponseList = new ArrayList<>();

        for (Job j: jobs){
            jobResponseList.add(this.toResponseDto(j));
        }
        return jobResponseList;
    }


}
