package com.tuespotsolutions.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "shortlistcompanyinbox")
public class ShortListCompanyInbox {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long candidateId;
	private Long jobId;
	private Long companyId;
	private Date createdOn;
	private Date modifiedOn;
	private String status;
	private Long notificationId;
	
}
