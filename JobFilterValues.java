package com.tuespotsolutions.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "jobfiltervalue")
public class JobFilterValues {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long filterValueId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private JobFilter jobFilter;
	
}
