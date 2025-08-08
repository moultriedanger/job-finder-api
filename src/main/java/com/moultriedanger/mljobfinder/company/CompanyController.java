package com.moultriedanger.mljobfinder.company;

import com.moultriedanger.mljobfinder.company.dto.CompanyResponse;
import com.moultriedanger.mljobfinder.job.Job;
import com.moultriedanger.mljobfinder.job.dto.JobResponse;
import com.moultriedanger.mljobfinder.job.mapper.JobResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CompanyController {

    private CompanyRepository companyRepository;
    private JobResponseMapper jobResponseMapper;

    CompanyController(CompanyRepository companyRepository, JobResponseMapper jobResponseMapper){
        this.companyRepository = companyRepository;
        this.jobResponseMapper = jobResponseMapper;
    }

    @GetMapping("/companies")
    public List<CompanyResponse> listAllCompanies(){
        return companyRepository.findAllCompanySummaries();
    }


    //Get company by company id
    @GetMapping("/companies/{id}")
    public CompanyResponse getCompanyById(@PathVariable Long id){

        Company c = companyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id));

        return new CompanyResponse(c.getCompanyId(), c.getCompanyName(), c.getCompanyDescription(), c.getCountryLocated(), c.getCompanyWebsite());
    }

    @GetMapping("/company-jobs")
    public List<List<Job>> listAllCompanyJobs(){

        List<List<Job>> jobs = new ArrayList<>();
        List<Company> companies = companyRepository.findAll();

        for (Company company:companies){
            jobs.add(company.getJobs());
        }

        return jobs;
    }

    //Get all jobs associated with a specified company
    @GetMapping("companies/{id}/jobs")
    public List<JobResponse> getJobsByCompanyId(@PathVariable Long id){

        Company c = companyRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Company not found with id: " + id));

        if (c.getJobs() == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no jobs associated with company id: " + id);

        List<Job> jobs = c.getJobs();

        List<JobResponse> jobResponseList = new ArrayList<>();

        for (Job j: jobs){

            jobResponseList.add(jobResponseMapper.toResponseDto(j));
        }

        return jobResponseList;
    }
}
