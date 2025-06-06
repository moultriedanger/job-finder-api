package com.moultriedanger.mljobfinder.company;

import com.moultriedanger.mljobfinder.company.dto.CompanyResponse;
import com.moultriedanger.mljobfinder.job.Job;
import com.moultriedanger.mljobfinder.job.dto.JobResponse;
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

    CompanyController(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @GetMapping("/companies")
    public List<CompanyResponse> listAllCompanies(){

        List<Company> companiesAndJobs = companyRepository.findAll();

        List<CompanyResponse> companies = new ArrayList<>();

        for (Company c: companiesAndJobs){
            companies.add(new CompanyResponse(
                    c.getCompanyName(),
                    c.getCompanyDescription(),
                    c.getCountryLocated(),
                    c.getCompanyWebsite()
            ));
        }

        return companies;
    }

    @GetMapping("/companies/{id}")
    public CompanyResponse getCompanyById(@PathVariable Long id){

        Company c = companyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id));

        return new CompanyResponse(c.getCompanyName(), c.getCompanyDescription(), c.getCountryLocated(), c.getCompanyWebsite());
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

            JobResponse job = new JobResponse();

            job.setJobTitle(j.getJobTitle());
            job.setJobDescription(j.getJobDescription());
            job.setSeniorityLevel(j.getSeniorityLevel());
            job.setMaxSalary(j.getMaxSalary());
            job.setLocation(j.getLocation());
            job.setPostingUrl(j.getPostingUrl());

            jobResponseList.add(job);
        }

        return jobResponseList;
    }
}
