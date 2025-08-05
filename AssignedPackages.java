package com.tuespotsolutions.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "assignedpackages")
public class AssignedPackages {
	
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
	private boolean status;
}
