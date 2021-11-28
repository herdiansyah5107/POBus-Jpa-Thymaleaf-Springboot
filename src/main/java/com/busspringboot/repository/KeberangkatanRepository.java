package com.busspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.busspringboot.model.Keberangkatan;
import com.busspringboot.model.Keberangkatandetail;



public interface KeberangkatanRepository extends JpaRepository<Keberangkatan, Long>{
	@Query(value ="SELECT keberangkatan.id, bus.nama_perusahaan as Perusahaan,keberangkatan.kelas, "
			+ "keberangkatan.harga,keberangkatan.tanggal as waktu, jurusan.deskripsi "+
			"from keberangkatan INNER JOIN jurusan ON keberangkatan.id_jurusan=jurusan.id "
			+ "INNER JOIN bus ON keberangkatan.no_polisi= bus.no_polisi"
			+  " where jurusan.terminal_awal =?1 && keberangkatan.tanggal Like ?2%"
			+ " AND bus.kapasitas >(select COUNT(*) from booking where booking.id_keberangkatan=keberangkatan.id)", 
			nativeQuery  =true)
	List<Keberangkatandetail> getDetail(String terminal_awal, String tanggal);

	
	
}
