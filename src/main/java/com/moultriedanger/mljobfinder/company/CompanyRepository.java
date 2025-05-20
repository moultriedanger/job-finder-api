package com.moultriedanger.mljobfinder.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    boolean existsByCompanyName(String companyName);
    Optional<Company> findByCompanyName(String companyName);
}