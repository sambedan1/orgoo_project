package com.tuespotsolutions.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@Entity
@Table(name = "candidateinboxnotification")
public class CandidateInboxNotification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long candidateId;
	private Long jobId;
	private Long companyId;
	private String createdOn;
	private String modifiedOn;
	private String status;
	private String statusSeen;
	@Column(nullable = true)
	private String approved;
	private boolean selfCheck;
	private Long currentStatus;
	private Long companyNotificationId;
	@Column(name = "message", columnDefinition = "TEXT")
	private String message;
	@Column(name = "subject", columnDefinition = "TEXT")
	private String subject;
	
}
