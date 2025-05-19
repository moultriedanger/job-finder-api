package com.moultriedanger.mljobfinder.job;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class JobController {

    private JobRepository repository;

    JobController(JobRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/jobs")
    List<Job> all() {
        return repository.findAll();
    }

    @GetMapping("/jobs/{id}")
    Job getJobById(@PathVariable Long id){

        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Job not found with id: " + id));
    }

}
