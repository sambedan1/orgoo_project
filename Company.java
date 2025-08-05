package com.tuespotsolutions.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/*
 *   Entity    : Company 
 *   CratedBy  : Baljinder Singh
 *   CreatedOn : 28/Feb/2023
 *   
 * */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String username;
	private String email;
	private String mobileNumber;
	
	@Column(name = "actual_Email", columnDefinition = "TEXT")
	private String actualEmail;
	
	@Column(name = "actual_cc_eamil", columnDefinition = "TEXT")
	private String actualCCEamil;
	
	@Column(name = "actual_bcc_email", columnDefinition = "TEXT")
	private String actualBCCEmail;
	
	private String whatsAppNumber;
	private String address;
	private Integer city;
	private Integer district;
	private Integer state;
	private Long pinCode;
	private String panCard;
	private String bussinessLocation;
	private String gstNo;
	private String logo;
	private String interviewLink;
	private java.util.Date createdOn;
	private java.util.Date modifiedOn;
	private boolean status;
	private String  flag;


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company")
	private List<Job> job;

	@Column(name = "storage")
	private String storage;
	
}
