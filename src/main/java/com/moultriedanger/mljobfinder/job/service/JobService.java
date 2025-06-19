package com.moultriedanger.mljobfinder.job.service;

import com.moultriedanger.mljobfinder.job.Job;
import com.moultriedanger.mljobfinder.job.JobRepository;
import com.moultriedanger.mljobfinder.job.dto.JobResponse;
import com.moultriedanger.mljobfinder.job.mapper.JobResponseMapper;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class JobService {

    private JobRepository jobRepository;
    private JobResponseMapper jobResponseMapper;

    public JobService(JobRepository jobRespository, JobResponseMapper jobResponseMapper){
        this.jobRepository = jobRespository;
    }

    public List<JobResponse> listAllJobs(){

        List<Job> jobList = jobRepository.findAll();
        return jobResponseMapper.toResponseDtoList(jobList);
    }

    public List<Job> searchJobs(String keyword){
        return jobRepository.searchJobs(keyword);
    }

}
