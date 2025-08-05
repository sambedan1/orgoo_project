package com.tuespotsolutions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="contactUs")
@Entity
@Data
public class ContactUs {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String organizationName;
	private String email;
	@Column(columnDefinition = "TEXT")
	private String message;
	private java.util.Date createdOn;
	private java.util.Date modifiedOn;

}
