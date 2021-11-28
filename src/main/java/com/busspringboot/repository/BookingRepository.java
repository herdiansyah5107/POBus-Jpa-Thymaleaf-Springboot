package com.busspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.busspringboot.model.Booking;
import com.busspringboot.model.BookingDetail;




public interface BookingRepository extends JpaRepository<Booking, Long>{
	
	@Query(value="SELECT booking.id, penumpang.nik, penumpang.nama, "
			+ "booking.id_keberangkatan, keberangkatan.tanggal as waktu, "
			+ "bus.nama_perusahaan as perusahaan, keberangkatan.kelas, keberangkatan.harga FROM `booking` "
			+ "INNER JOIN penumpang on booking.nik=penumpang.nik "
			+ "INNER join keberangkatan on booking.id_keberangkatan=keberangkatan.id "
			+ "INNER join bus on keberangkatan.no_polisi = bus.no_polisi "
			+ "where booking.id_keberangkatan =?1 && booking.nik =?2",nativeQuery  =true)
			List<BookingDetail> getDetail(long id_keberangkatan, String nik);

	

	
		
			


	

}
