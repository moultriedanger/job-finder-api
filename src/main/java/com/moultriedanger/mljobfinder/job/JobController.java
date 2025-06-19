package com.moultriedanger.mljobfinder.job;
import java.util.ArrayList;
import java.util.List;

import com.moultriedanger.mljobfinder.company.Company;
import com.moultriedanger.mljobfinder.company.CompanyRepository;
import com.moultriedanger.mljobfinder.company.dto.CompanyResponse;
import com.moultriedanger.mljobfinder.company.mapper.CompanyResponseMapper;
import com.moultriedanger.mljobfinder.job.dto.JobRequest;
import com.moultriedanger.mljobfinder.job.dto.JobResponse;
import com.moultriedanger.mljobfinder.job.mapper.JobRequestMapper;
import com.moultriedanger.mljobfinder.job.mapper.JobResponseMapper;
import com.moultriedanger.mljobfinder.job.service.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
public class JobController {

    private JobRepository jobRepository;
    private CompanyRepository companyRepository;
    private JobResponseMapper jobResponseMapper;
    private JobRequestMapper jobRequestMapper;
    private JobService jobService;
    private CompanyResponseMapper companyResponseMapper;

    public JobController(JobRepository jobRepository, CompanyRepository companyRepository, JobResponseMapper jobResponseMapper, JobService jobService, CompanyResponseMapper companyResponseMapper, JobRequestMapper jobRequestMapper) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.jobResponseMapper = jobResponseMapper;
        this.jobService = jobService;
        this.companyResponseMapper = companyResponseMapper;
        this.jobRequestMapper = jobRequestMapper;
    }

    /*
    Returns all job entities
    */
    @GetMapping("/jobs")
    public List<JobResponse> all() {
        return jobService.listAllJobs();
    }

    /*
    Returns job entity given a specified id
    */
    @GetMapping("/jobs/{id}")
    public JobResponse getJobById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    /*
    Returns a companyResponse for a job given the job id
    */
    @GetMapping("/jobs/{id}/company")
    public CompanyResponse getCompanyByJobId(@PathVariable Long id){
        return jobService.getCompanyByJobId(id);
    }

    /*
    Adds a job entity to the database given a JobRequestDto
    */
    @PostMapping("/jobs")
    public ResponseEntity<Job> addJob(@Valid @RequestBody JobRequest jobRequestDTO){
        return jobService.addJob(jobRequestDTO);
    }


    /*
      Updates a job entity in the database given an id and JobRequestDto
     */
    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@Valid @PathVariable Long id, @RequestBody JobRequest jobRequestDTO){
        return jobService.updateJob(id, jobRequestDTO);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<JobResponse> deleteJobById(@Valid @PathVariable Long id){
        return jobService.deleteJobById(id);
    }

    /*
    Returns a list of jobResponses based on the keyword
    */
    @GetMapping("/jobs/search")
    public List<JobResponse> searchJob(@RequestParam String keyword){
        List<Job> jobs = jobService.searchJobs(keyword);

        List<JobResponse> jobResponseList = new ArrayList<>();

        //need a mapper methiod for this
        for (Job j : jobs){
            jobResponseList.add(jobResponseMapper.toResponseDto(j));
        }

        return jobResponseList;
    }
}
