package com.moultriedanger.mljobfinder.company.mapper;

import com.moultriedanger.mljobfinder.company.Company;
import com.moultriedanger.mljobfinder.company.dto.CompanyResponse;
import org.springframework.stereotype.Component;

@Component
public class CompanyResponseMapper {

    public CompanyResponse toCompanyResponse(Company company){
        CompanyResponse companyResponse = new CompanyResponse(
            company.getCompanyId(),
            company.getCompanyName(),
            company.getCompanyDescription(),
            company.getCountryLocated(),
            company.getCompanyWebsite()
        );

        return companyResponse;
    }
}
