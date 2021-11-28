package com.busspringboot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="penumpang")
@Entity
public class Penumpang {


	@Id
	private String nik;
	private String nama;
	private String telpon;
	
}
