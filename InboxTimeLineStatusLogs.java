package com.tuespotsolutions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "inbox_timeline_status_logs")
public class InboxTimeLineStatusLogs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long notificationId;
	private Long companyId;
	private Long candidateId;
	private Long jobId;
	private Long statusId;
	private String role;
	private String createdOn;
	private String modifiedOn;
	@Column(name = "remarks", columnDefinition = "TEXT")
	private String remarks;
	
}
