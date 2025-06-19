package com.moultriedanger.mljobfinder.job.service;

import com.moultriedanger.mljobfinder.job.Job;
import com.moultriedanger.mljobfinder.job.JobRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class JobService {

    private JobRepository jobRepository;

    public JobService(JobRepository jobRespository){
        this.jobRepository = jobRespository;
    }

    public List<Job> searchJobs(String keyword){
        return jobRepository.searchJobs(keyword);
    }

}
