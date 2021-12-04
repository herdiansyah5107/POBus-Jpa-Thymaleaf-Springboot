package com.busspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.busspringboot.model.Keberangkatan;
import com.busspringboot.model.Keberangkatandetail;
import com.busspringboot.model.KursiKosong;



public interface KeberangkatanRepository extends JpaRepository<Keberangkatan, Long>{
	@Query(value ="SELECT keberangkatan.id, bus.nama_perusahaan as Perusahaan,keberangkatan.kelas, "
			+ "keberangkatan.harga,keberangkatan.tanggal as waktu, jurusan.deskripsi "+
			"from keberangkatan INNER JOIN jurusan ON keberangkatan.id_jurusan=jurusan.id "
			+ "INNER JOIN bus ON keberangkatan.no_polisi= bus.no_polisi"
			+  " where jurusan.terminal_awal =?1 && keberangkatan.tanggal Like ?2%"
			+ " AND bus.kapasitas >(select COUNT(*) from booking where booking.id_keberangkatan=keberangkatan.id)", 
			nativeQuery  =true)
	List<Keberangkatandetail> getDetail(String terminal_awal, String tanggal);
	
	@Query(value ="SELECT booking.id_keberangkatan, keberangkatan.tanggal as waktu, "
			+ "jurusan.terminal_awal, jurusan.terminal_akhir, keberangkatan.kelas, "
			+ "keberangkatan.harga, bus.nama_perusahaan as Perusahaan, "
			+ "if(bus.kapasitas-count(booking.id_keberangkatan)=0,\"Full\",bus.kapasitas-count(booking.id_keberangkatan)) as kursi_free "
			+ "from keberangkatan LEFT join booking on keberangkatan.id=booking.id_keberangkatan LEFT JOIN "
			+ "jurusan on keberangkatan.id_jurusan=jurusan.id LEFT JOIN bus on keberangkatan.no_polisi=bus.`no_polisi` "
			+ "GROUP by keberangkatan.`id`", nativeQuery =true)
			List<KursiKosong> getAll();

	



	
	
}
