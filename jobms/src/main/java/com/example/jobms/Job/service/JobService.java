package com.example.jobms.Job.service;


import com.example.jobms.Job.dto.JobDTO;
import com.example.jobms.Job.entity.Job;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);

    JobDTO getJobById (Long id);

    boolean deleteJobById (Long id);

    boolean updateJob (Long id, Job updatedJob);
}
