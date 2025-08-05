package com.tuespotsolutions.entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "paymentgateways")
public class PaymentGateways {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String paymentGatwayName;
	private String paymentGatwayLogo;
	private Boolean status;
	private Date createdOn;
	private Date modifiedOn;
	private String paymentGatewayUrl;
	@Column(name = "storage")
	private String storage;
	
}
