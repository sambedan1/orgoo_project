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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jobapplied")
public class JobApplied {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date createdOn;
	private Date modifiedOn;
	private boolean status;
	private Long companyId;
	@Column(nullable = true)
	private String currentStatus;
	@ManyToOne(fetch = FetchType.LAZY)
	private Job job;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Candidate candidates;
	
	@Column(name = "meeting_between", columnDefinition = "TEXT")
	private String meetingBetween;
	
	@Column(name = "meeting_link", columnDefinition = "TEXT")
	private String meetingLink;
	private String startTime;
	private String endTime;
	
}
