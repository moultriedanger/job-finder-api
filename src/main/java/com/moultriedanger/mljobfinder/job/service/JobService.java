package com.moultriedanger.mljobfinder.job.service;

import com.moultriedanger.mljobfinder.company.Company;
import com.moultriedanger.mljobfinder.company.CompanyRepository;
import com.moultriedanger.mljobfinder.company.dto.CompanyResponse;
import com.moultriedanger.mljobfinder.company.mapper.CompanyResponseMapper;
import com.moultriedanger.mljobfinder.job.Job;
import com.moultriedanger.mljobfinder.job.JobRepository;
import com.moultriedanger.mljobfinder.job.dto.JobRequest;
import com.moultriedanger.mljobfinder.job.dto.JobResponse;
import com.moultriedanger.mljobfinder.job.mapper.JobRequestMapper;
import com.moultriedanger.mljobfinder.job.mapper.JobResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final JobRequest jobRequest;
    private final JobResponse jobResponse;
    private final JobRequestMapper jobRequestMapper;
    private final JobResponseMapper jobResponseMapper;

    private final CompanyRepository companyRepository;
    private final CompanyResponseMapper companyResponseMapper;

    public JobService(JobRepository jobRepository, JobRequest jobRequest, JobResponse jobResponse, JobRequestMapper jobRequestMapper, JobResponseMapper jobResponseMapper, CompanyRepository companyRepository, CompanyResponseMapper companyResponseMapper) {
        this.jobRepository = jobRepository;
        this.jobRequest = jobRequest;
        this.jobResponse = jobResponse;
        this.jobRequestMapper = jobRequestMapper;
        this.jobResponseMapper = jobResponseMapper;
        this.companyRepository = companyRepository;
        this.companyResponseMapper = companyResponseMapper;
    }

    /**
     * Retrieves all job entities from the repository and maps them to DTOs.
     *
     * @return List of JobResponse DTOs representing all jobs.
     */
    public List<JobResponse> listAllJobs(){

        List<Job> jobList = jobRepository.findAll();
        return jobResponseMapper.toResponseDtoList(jobList);
    }

    /**
     * Retrieves job entity from the repository from a given id and mapped to a DTO.
     *
     * @return JobResponse DTO.
     */
    public JobResponse getJobById(Long id){

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found with id: " + id));

        return jobResponseMapper.toResponseDto(job);
    }


    /**
     * Retrieves company entity from the companyRepository from a given job id and mapped to a DTO.
     *
     * @return CompanyResponse DTO.
     */
    public CompanyResponse getCompanyByJobId(Long id){

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found with id: " + id));

        Company company = job.getCompany();

        return companyResponseMapper.toCompanyResponse(company);
    }

    /**
     * Adds job
     *
     * @return ResponseEntity<Job>
     */
    public ResponseEntity<Job> addJob(JobRequest jobDTO){

        Company company = companyRepository.findById(jobDTO.getCompanyId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + jobDTO.getCompanyId()));

        Job job = jobRequestMapper.toJobEntity(jobDTO, company);

        jobRepository.save(job);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    /**
     * Updates a job entity in the database given an id and JobRequestDto
     *
     * @return ResponseEntity<Job>
     */
    public ResponseEntity<Job> updateJob(Long id, JobRequest jobDTO){

        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found with id: " + id));

        Company company = companyRepository.findById(jobDTO.getCompanyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id));

        Company previousCompany = job.getCompany();

        //Convert request body to job entity
        job = jobRequestMapper.toJobEntity(jobDTO, company);

        //Delete the previous job from the company provided
        List<Job> previousCompanyJobs = previousCompany.getJobs();

        for (Job j: previousCompanyJobs){
            if (j.getJobId().equals(job.getJobId())){
                previousCompanyJobs.remove(j);
                break;
            }
        }

        return new ResponseEntity<Job>(job, HttpStatus.OK);
    }


    public List<Job> searchJobs(String keyword){
        return jobRepository.searchJobs(keyword);
    }

}
