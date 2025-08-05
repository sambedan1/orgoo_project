package com.tuespotsolutions.entity;


import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/*
 *   Entity    : Candidate 
 *   CratedBy  : Baljinder Singh
 *   CreatedOn : 28/Feb/2023
 *   
 * */


@Data
@Entity
@Table(name = "candidate")
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonProperty("name")
	private String name;
	private String username;
	private String email;
	private String mobileNumber;
	private String address;
	private Integer city;
	private Integer district;
	private Integer state;
	private Long pinCode;
	private String panCard;
	private String resume;
	private java.sql.Date dateOfBirth;
	private boolean status;
	private Date createdOn;
	private Date modifiedOn;
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	private String image;
	private String profileHeadline;
	private String experience;
	private boolean isCommentDisable;
	
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "candidate")
	private List<CandidateEducation> candidateEducation;
	
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "candidate")
	private List<CandidateExperience> candidateExperiences;
	
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "candidate")
	private List<CandidateLanguages> candidateLanguages;
	
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "candidate")
	private List<CandidateProjects> candidateProjects;
	
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "candidate")
	private List<CandidateSalary> candidateSalaries;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "candidates")
	private List<JobApplied> jobApplieds;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "candidate")
	private List<CandidateSkills> candidateSkills;

	@Column(name = "storage")
	private String storage;

}
