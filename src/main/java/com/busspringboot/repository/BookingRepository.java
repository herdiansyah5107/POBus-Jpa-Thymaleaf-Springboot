package com.busspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.busspringboot.model.Booking;
import com.busspringboot.model.BookingDetail;

import com.busspringboot.model.Keberangkatandetail;

import com.busspringboot.model.Penumpang;




public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	@Query(value="SELECT booking.id, penumpang.nik, penumpang.nama, "
<<<<<<< HEAD
			+ "booking.id_keberangkatan, keberangkatan.tanggal as waktu, "
			+ "bus.nama_perusahaan as perusahaan, keberangkatan.kelas, "
			+ "keberangkatan harga FROM `booking` "
			+ "INNER JOIN penumpang on booking.nik=penumpang.nik "
			+ "INNER join keberangkatan on booking.id_keberangkatan=keberangkatan.id "
			+ "INNER join bus on keberangkatan.no_polisi = bus.no_polisi "
			+ "where booking.id_keberangkatan =?1 && booking.nik =?2",nativeQuery  =true)
			List<BookingDetail> getDetail(long id_keberangkatan, String nik);

			@Query(value ="SELECT keberangkatan.id, bus.nama_perusahaan as Perusahaan,"
			+ "keberangkatan.kelas, keberangkatan.harga,keberangkatan.tanggal as waktu,"
			+ " jurusan.deskripsi from keberangkatan INNER JOIN jurusan ON "
			+ "keberangkatan.id_jurusan=jurusan.id "
			+ "INNER JOIN bus ON keberangkatan.no_polisi= bus.no_polisi"
			+  " where booking.id_keberangkatan =?1 "
			+ "AND bus.kapasitas >(select COUNT(*) from booking where"
			+" booking.id_keberangkatan=keberangkatan.id)", 
			nativeQuery  =true)
			List<Keberangkatandetail> getDetail(long id_keberangkatan);

			List<Booking> findByNik(Penumpang nik);
=======
		+ "booking.id_keberangkatan, keberangkatan.tanggal as waktu, "
		+ "bus.nama_perusahaan as perusahaan, keberangkatan.kelas, keberangkatan.harga FROM `booking` "
		+ "INNER JOIN penumpang on booking.nik=penumpang.nik "
		+ "INNER join keberangkatan on booking.id_keberangkatan=keberangkatan.id "
		+ "INNER join bus on keberangkatan.no_polisi = bus.no_polisi "
		+ "where booking.id_keberangkatan =?1 && booking.nik =?2 ",nativeQuery  =true)
	List<BookingDetail> getDetail(long id_keberangkatan, String nik);

	List<Booking> findByNik(Penumpang nik);
>>>>>>> 6f56232b44448f2d6e3a9865f3938bc8a0c735be

			
		






	
		
			


	

}
