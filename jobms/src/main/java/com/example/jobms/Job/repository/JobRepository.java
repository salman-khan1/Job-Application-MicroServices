package com.example.jobms.Job.repository;

import com.example.jobms.Job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
