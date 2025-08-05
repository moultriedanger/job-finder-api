package com.moultriedanger.mljobfinder;

import com.moultriedanger.mljobfinder.company.Company;
import com.moultriedanger.mljobfinder.company.CompanyRepository;
import com.moultriedanger.mljobfinder.job.Job;
import com.moultriedanger.mljobfinder.job.JobRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.*;
import java.util.Arrays;
import java.util.Optional;

import com.opencsv.CSVReader;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("!test")
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Transactional
    @Bean
    CommandLineRunner initJobs(JobRepository jobRepository, CompanyRepository companyRepository) {
        return args -> {

            if (companyRepository.count() > 0) {
                System.out.println("Database already populated. Skipping seed data.");
                return;
            }

            try (CSVReader reader = new CSVReader(new FileReader("/Users/moultriedangerfield/Desktop/mljobfinder/src/main/resources/data/jobs_and_companies.csv"))) {
                String[] row;
                reader.readNext();
                
                while ((row = reader.readNext()) != null) {

                    //Job parse logic
                    String title = row[0];
                    String description = row[1];
                    String maxSalary = row[2];
                    String payPeriod = row[3];
                    String location = row[4];
                    String seniorityLevel = row[5];
                    String postingUrl = row[6];

                    Job job = new Job(title, description, seniorityLevel, maxSalary, location, postingUrl);

                    //company parse logic
                    String companyName = row[8];
                    String companyDescription = row[9];
                    String countryLocated = row[10];
                    String companyWebsite = row[11];

                    Optional<Company> company = companyRepository.findByCompanyName(companyName);

                    if (company.isPresent()) {
                        Company c = company.get();
                        c.addJob(job);
                        companyRepository.save(c);
                    }

                    else{
                        Company newCompany = new Company(companyName, companyDescription, companyWebsite, countryLocated);
                        newCompany.addJob(job);

                        companyRepository.save(newCompany);
                    }
                    System.out.println("Company count: " + companyRepository.findAll().size());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}