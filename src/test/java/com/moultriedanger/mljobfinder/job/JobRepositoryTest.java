package com.moultriedanger.mljobfinder.job;

import com.moultriedanger.mljobfinder.company.Company;
import com.moultriedanger.mljobfinder.company.CompanyRepository;
import com.moultriedanger.mljobfinder.company.companyBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void testFindById(){
        //Arrange
        Company company = new companyBuilder()
                .withCompanyName("Microsoft")
                .build();

        companyRepository.save(company);

        Job job = new JobBuilder()
                .withJobTitle("Data Engineer")
                .withCompany(company)
                .build();

        jobRepository.save(job);

        // Act
        Long jobId = job.getJobId();

        Optional<Job> retrieved = jobRepository.findById(jobId);

        // Assert
        assertTrue(retrieved.isPresent());
        assertEquals("Data Engineer", retrieved.get().getJobTitle());
        assertEquals("Microsoft", retrieved.get().getCompany().getCompanyName());
    }
}
