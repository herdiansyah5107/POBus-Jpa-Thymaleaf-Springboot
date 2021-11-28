package com.busspringboot.model;

import javax.persistence.Entity;

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
@Table(name = "bus")
@Entity
public class Bus {
	
	@Id
	private String no_polisi; 
	private int kapasitas;
	private String nama_supir;
	@ManyToOne
	@JoinColumn(name="nama_perusahaan",referencedColumnName = "nama")
	private Perusahaan nama_perusahaan;

	
}
