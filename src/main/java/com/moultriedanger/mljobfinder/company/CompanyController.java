package com.moultriedanger.mljobfinder.company;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
