package com.moultriedanger.mljobfinder.company;

import com.moultriedanger.mljobfinder.company.dto.CompanyResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByCompanyName(String companyName);
    Optional<Company> findByCompanyName(String companyName);

    @Query("""
      SELECT new com.moultriedanger.mljobfinder.company.dto.CompanyResponse(
        c.id,
        c.companyName,
        c.companyDescription,
        c.countryLocated,
        c.companyWebsite
      )
      FROM Company c
    """)
    List<CompanyResponse> findAllCompanySummaries();
}