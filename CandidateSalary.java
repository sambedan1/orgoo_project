package com.tuespotsolutions.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/*
 *   Entity    : CandidateSalary 
 *   CratedBy  : Baljinder Singh
 *   CreatedOn : 28/Feb/2023
 *   
 * */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidate_salary")
public class CandidateSalary {
		@Id
		@GeneratedValue
		private Long id;
		private String currentCtc;
		private String expectedCtc;
		private String noticePeriod;
		private String ctcBreakup;
		private String salaryInHandBreakup;
		@ManyToOne(fetch = FetchType.LAZY)
		private Candidate candidate;

}
