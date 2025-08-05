package com.tuespotsolutions.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "job")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	// Remote / office
	private String location;
	// partime / fulltime
	private String jobType;
	private String experience;
	private String skills;
	private String createOn;
	private String modifiedOn;
	//active/ inactive
	private boolean status;
	private String department;
	@Column(name = "bot_job_id", columnDefinition = "TEXT")
	private String botJobId;	
	private String botName;
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "job")
	private List<JobApplied> applied;
	
	
}
