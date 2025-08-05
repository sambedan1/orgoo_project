package com.tuespotsolutions.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
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
 *   Entity    : CandidateExperience 
 *   CratedBy  : Baljinder Singh
 *   CreatedOn : 28/Feb/2023
 *   
 * */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate_experience")
public class CandidateExperience {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean current;
	private String employmentType;
	private String companyName;
	private String designation;
	private Date joingDate;
	private Date endDate;
	private String ctc;
	private String jobProfile;
	private String inHandSalary;
	private String ctcBreakup;
	private String salaryInHandBreakup;
	
	@ManyToOne
	private Candidate candidate;
}
