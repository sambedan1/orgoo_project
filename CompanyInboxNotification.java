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
@Table(name = "companyinboxnotification")
public class CompanyInboxNotification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long candidateId;
	private Long jobId;
	private Long companyId;
	private String createdOn;
	private String modifiedOn;
	private String status;
	private Long currentStatus;
	private String statusSeen;
	private boolean selfCheck;
	@Column(nullable = true)
	private String approved;
	private Long candidateNotificationId;
	@Column(name = "message", columnDefinition = "TEXT")
	private String message;
	@Column(name = "subject", columnDefinition = "TEXT")
	private String subject;
	
}
