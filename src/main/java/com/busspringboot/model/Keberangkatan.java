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
@Table(name="keberangkatan")
@Entity
public class Keberangkatan {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private int harga;
	private String kelas;
	private String tanggal;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_jurusan", referencedColumnName = "id")
	Jurusan id_jurusan;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "no_polisi", referencedColumnName = "no_polisi")
	Bus no_polisi;

}
