package com.tuespotsolutions.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "packages")
public class Packages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer days;
	private double price;
	//free/paid
	private String type;
	private double discount;
	private boolean company;
	private boolean candidate;
	private Date createdOn;
	private Date modifiedOn;
	private Long createdBy;
	private Long modifiedBy;
	private boolean status;
	private boolean defaultPackage;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "packages")
	private List<PackageDiscription> packageDiscriptions;
    
}
