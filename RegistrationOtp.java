package com.tuespotsolutions.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "registration_otp")
public class RegistrationOtp {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String otpRefrenceId;
	private String otp;
	private Long userId;
	private String userType;
	private Date createdOn;
	private Date modifiedOn;
}
