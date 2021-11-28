package com.busspringboot.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "booking")
@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	 private long id;
	
	 @ManyToOne(cascade = CascadeType.MERGE)
	 @JoinColumn(name = "id_keberangkatan", referencedColumnName = "id")
	 Keberangkatan id_keberangkatan;
	 
	 @ManyToOne(cascade = CascadeType.MERGE)
	 @JoinColumn(name = "nik", referencedColumnName = "nik")
	 Penumpang nik;



}
