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
@Table(name="perusahaan")
@Entity
public class Perusahaan {
	
	@Id
	private String nama;
	private String alamat;
}
