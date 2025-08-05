package com.tuespotsolutions.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transcation")
public class Transcation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long planeId;
	private String planeAmount;
	private Long userId;
	private String type;
	private String transactionId;
	private Long amount;
	private String tokenTranscationId;
	@Column(name = "transaction_response", columnDefinition = "TEXT")
	private String transactionResponse;
	private String customerEmail;
	private String customerMobileNumber;
	private Date createdOn;
	private Date modifiedOn;
	private String status;
	
}
