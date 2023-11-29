package com.example.jobms.Job.service.imp;

import com.example.jobms.Job.clients.CompanyClient;
import com.example.jobms.Job.clients.ReviewClient;
import com.example.jobms.Job.dto.JobDTO;
import com.example.jobms.Job.external.Company;
import com.example.jobms.Job.entity.Job;
import com.example.jobms.Job.external.Review;
import com.example.jobms.Job.mapper.JobMapper;
import com.example.jobms.Job.repository.JobRepository;
import com.example.jobms.Job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

//    private List<Job> jobs=new ArrayList<> ();
    @Autowired
    JobRepository jobRepository;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private ReviewClient reviewClient;
    @Override
    public List<JobDTO> findAll () {
        List<Job> jobs=jobRepository.findAll ();
        List<JobDTO> jobDTO =new ArrayList<> ();

     return jobs.stream ().map (this::convertToDto)
             .collect (Collectors.toList ());
    }
private JobDTO convertToDto(Job job){

//        Company company= restTemplate.getForObject (
//                "http://COMPANY-SERVICE:8081/companies/"+ job.getCompanyId (),
//                Company.class);

    Company company=companyClient.getCompany(job.getCompanyId ());


//        ResponseEntity<List<Review>> reviewResponse= restTemplate
//                .exchange ("http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId (),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>> () {
//                });
//        List<Review> reviews=reviewResponse.getBody ();


    List<Review> reviews=reviewClient.getReview (job.getCompanyId ());



    JobDTO jobDto = JobMapper.
            mapToJobWithCompanyDto (job,company,reviews);

//    jobDto.setCompany (company);
       return jobDto;

}
    @Override
    public void createJob (Job job) {
         jobRepository.save (job);
    }

    @Override
    public JobDTO getJobById (Long id) {

        Job job = jobRepository.findById (id).orElse (null);
        return convertToDto (job);
    }

    public boolean deleteJobById(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty ()) {
            return false;
        }
        jobRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateJob (Long id, Job updatedJob) {
        Optional<Job> jobOptional=jobRepository.findById (id);

            if(jobOptional.isPresent ()){
                Job job=jobOptional.get ();
                job.setTitle (updatedJob.getTitle ());
                job.setDescription (updatedJob.getDescription ());
                job.setMaxSalary (updatedJob.getMaxSalary ());
                job.setMinSalary (updatedJob.getMinSalary ());
                job.setLocation (updatedJob.getLocation ());
                jobRepository.save (job);
                return true;
            }

        return false;
    }
}
