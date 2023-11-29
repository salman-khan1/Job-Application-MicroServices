package com.example.jobms.Job.external;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Company {

    private Long id;
    private String name;
    private String description;

//    @JsonIgnore
//    @OneToMany(mappedBy = "company")
//    private List<Job> jobs;
//
//    @OneToMany(mappedBy = "company")
//    private List<Review> reviews;
}


