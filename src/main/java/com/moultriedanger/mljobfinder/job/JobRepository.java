package com.moultriedanger.mljobfinder.job;

import com.moultriedanger.mljobfinder.job.dto.JobResponse;
import jakarta.persistence.Lob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("SELECT j FROM Job j WHERE " +
            "LOWER(j.jobTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(j.jobDescription) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Job> searchJobs(@Param("keyword") String keyword);


    @Query(
            "SELECT new com.moultriedanger.mljobfinder.job.dto.JobResponse(" +
                    "  j.id, " +
                    "  j.jobTitle, " +
                    "  j.jobDescription, " +
                    "  j.seniorityLevel, " +
                    "  j.maxSalary, " +
                    "  j.location, " +
                    "  j.postingUrl, " +
                    "  j.company.companyName" +
                    ") " +
                    "FROM Job j"
    )
    List<JobResponse> findAllJobResponses();

}