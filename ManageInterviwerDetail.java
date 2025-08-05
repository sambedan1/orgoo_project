package com.tuespotsolutions.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "manage_interviwer_detail")
public class ManageInterviwerDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long jobId;
	private long candidateId;
	private long companyId;
	private String createdOn;
	private String modifiedOn;
	private String remarks;
	
	

}
