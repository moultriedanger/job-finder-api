package com.moultriedanger.mljobfinder.company;

import com.moultriedanger.mljobfinder.job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
