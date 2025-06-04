package com.moultriedanger.mljobfinder.company;

import com.moultriedanger.mljobfinder.job.Job;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompanyController {

    private CompanyRepository companyRepository;

    CompanyController(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    @GetMapping("/companies")
    public List<Company> listAllCompanies(){

        return companyRepository.findAll();
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

}
