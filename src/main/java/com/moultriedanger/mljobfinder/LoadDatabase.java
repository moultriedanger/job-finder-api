package com.moultriedanger.mljobfinder;

import com.moultriedanger.mljobfinder.job.Job;
import com.moultriedanger.mljobfinder.job.JobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.*;
import java.util.Arrays;
import com.opencsv.CSVReader;


@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initJobs(JobRepository repository) {
        return args -> {
            try (CSVReader reader = new CSVReader(new FileReader("/Users/moultriedangerfield/Desktop/mljobfinder/src/main/resources/data/1000_ml_jobs_us.csv"))) {
                String[] row;
                reader.readNext(); // Skip header row
                while ((row = reader.readNext()) != null) {
                    String title = row[9];   // example index for "title"
                    String level = row[8];   // example index for "level"


                    String description = row[7];// example index for job description

                    if (description.length() >= 10){
                        description = row[7].substring(0,9);
                    }

                    repository.save(new Job(title, description, level));
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