package com.tuespotsolutions.entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
 *   Entity    : CandidateProjects 
 *   CratedBy  : Baljinder Singh
 *   CreatedOn : 28/Feb/2023
 *   
 * */


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate_projects")
public class CandidateProjects {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String client;
	private boolean projectStatus;
	private Date startDate;
	private Date endDate;
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	private String location;
	private String natureOfEmployement;
	private Integer teamSize;
	private String role;
	@Column(name = "role_description", columnDefinition = "TEXT")
	private String roleDescription;
	private String skillSet;

	@ManyToOne(fetch = FetchType.LAZY)
	private Candidate candidate;

}
