package com.moultriedanger.mljobfinder;

import com.moultriedanger.mljobfinder.company.Company;
import com.moultriedanger.mljobfinder.company.CompanyRepository;
import com.moultriedanger.mljobfinder.job.Job;
import com.moultriedanger.mljobfinder.job.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.*;
import java.util.Arrays;
import java.util.Optional;

import com.opencsv.CSVReader;


@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initJobs(JobRepository jobRepository, CompanyRepository companyRepository) {
        return args -> {
            try (CSVReader reader = new CSVReader(new FileReader("/Users/moultriedangerfield/Desktop/mljobfinder/src/main/resources/data/1000_ml_jobs_us.csv"))) {
                String[] row;
                reader.readNext();
                
                while ((row = reader.readNext()) != null) {

                    //Job Parse logic
                    String title = row[8];
                    String level = row[9];
                    String description = row[7];

                   //messy logic- eventually change to handle nulll descriptions
                    if (description.length() >= 10){
                        description = row[7].substring(0,9);
                    }

                    Job job = new Job(title, description, level);

                    //Company parse logic
                    //need to check if company exists in the company repository
                    String companyName = row[4];
                    String companyWebsite = row[5];

                    String companyDescription = row[6];
                    if (companyDescription.length() >= 10){
                        companyDescription = row[6].substring(0,9);
                    }

                    Optional<Company> company = companyRepository.findByCompanyName(companyName);

                    if (company.isPresent()) {
                        Company c = company.get();
                        c.addJob(job);
                        companyRepository.save(c);
                    }

                    else{
                        Company newCompany = new Company(companyName, companyDescription, companyWebsite);
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

//repository.findAll();        // Like getting all values from a list
//repository.findById(id);     // Like a map lookup by key
//repository.save(obj);        // Like inserting into a list or map
//repository.delete(obj);      // Like removing from a list
//repository.existsById(id);   // Like map.containsKey