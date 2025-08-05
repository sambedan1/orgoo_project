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
@Table(name = "qr_code_buy_packages")
public class BuyPackageByQrCodeScanner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long packageId;
	private String assignDate;
	private String endDate;
	private Integer  assignedDays;
	private Integer pendingDays;
	private Long userId;
	private String userType;
	private String status;
	@Column(name = "qr_code", columnDefinition = "TEXT")
	private String qrCode;
	private String amount;
	
}
