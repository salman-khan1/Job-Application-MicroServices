package com.example.jobms.Job.controller;

import com.example.jobms.Job.dto.JobDTO;
import com.example.jobms.Job.entity.Job;
import com.example.jobms.Job.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    public JobController (JobService jobService) {
        this.jobService = jobService;
    }

    private JobService jobService;
    @GetMapping("/jobs")
    public ResponseEntity<List<JobDTO>> findAll(){

        return ResponseEntity.ok(jobService.findAll ());
    }
    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id){
        JobDTO jobDto = jobService.getJobById (id);
       if(jobDto !=null){
       return new ResponseEntity<> (jobDto, HttpStatus.OK);}
       return new ResponseEntity<> (HttpStatus.NOT_FOUND);
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob (job);
//        Company company=job.getCompany ();

        return new ResponseEntity<> ("Job Added Successfully",HttpStatus.CREATED);
    }

    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
       boolean deleted= jobService.deleteJobById(id);
       if(deleted){
           return new ResponseEntity<> ("Job Deleted Successfully",HttpStatus.OK);
       }
        return new ResponseEntity<> ("Job with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id,
                                            @RequestBody Job updatedJob){
        boolean updated=jobService.updateJob(id,updatedJob);
        if(updated){
            return new  ResponseEntity<>("Job Updated Successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
