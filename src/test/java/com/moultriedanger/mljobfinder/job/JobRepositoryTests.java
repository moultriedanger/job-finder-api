package com.moultriedanger.mljobfinder.job;

import com.moultriedanger.mljobfinder.company.Company;
import com.moultriedanger.mljobfinder.company.CompanyRepository;
import com.moultriedanger.mljobfinder.company.CompanyBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class JobRepositoryTests {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    void JobRepository_FindById_ReturnsOneJobById(){
        //Arrange
        Company company = new CompanyBuilder().withCompanyName("Microsoft").build();
        companyRepository.save(company);

        Job job = new JobBuilder().withJobTitle("Data Engineer").withCompany(company).build();

        jobRepository.save(job);

        // Act
        Long jobId = job.getJobId();

        Optional<Job> retrieved = jobRepository.findById(jobId);

        // Assert
        assertTrue(retrieved.isPresent());
        assertEquals("Data Engineer", retrieved.get().getJobTitle());
        assertEquals("Microsoft", retrieved.get().getCompany().getCompanyName());
    }

    @Test
    public void JobRepository_GetAll_ReturnMoreThanOneJob() {
        Company company = new CompanyBuilder().withCompanyName("Microsoft").build();
        companyRepository.save(company);

        Job job1 = new JobBuilder().withJobTitle("Data Engineer").withCompany(company).build();
        Job job2 = new JobBuilder().withJobTitle("System Admin").withCompany(company).build();

        jobRepository.save(job1);
        jobRepository.save(job2);

        List<Job> jobs = jobRepository.findAll();

        assertThat(jobs)
                .hasSize(2)
                .isNotNull()
                .extracting(Job::getJobTitle)
                .containsExactlyInAnyOrder("Data Engineer", "System Admin");
    }
}
