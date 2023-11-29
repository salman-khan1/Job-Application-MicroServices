package com.example.jobms.Job.mapper;
import com.example.jobms.Job.dto.JobDTO;
import com.example.jobms.Job.entity.Job;
import com.example.jobms.Job.external.Company;
import com.example.jobms.Job.external.Review;
import java.util.List;
public class JobMapper {
    public static JobDTO mapToJobWithCompanyDto(Job job,
                                                Company company,
                                                List<Review> reviews)
    {
        JobDTO jobDto =new JobDTO ();
        jobDto.setId (job.getId ());
        jobDto.setTitle (job.getTitle ());
        jobDto.setDescription (job.getDescription ());
        jobDto.setLocation (job.getLocation ());
        jobDto.setMaxSalary (job.getMaxSalary ());
        jobDto.setMinSalary (job.getMinSalary ());
        jobDto.setCompany (company);
        jobDto.setReview (reviews);
        return jobDto;
    }
}
