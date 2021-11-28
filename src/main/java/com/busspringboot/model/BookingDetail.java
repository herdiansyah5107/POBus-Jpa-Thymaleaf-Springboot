package com.busspringboot.model;

//untuk menampilkan pemanggilan
public interface BookingDetail {
	long getId();
	String getnik();
	String getnama();
	long getId_Keberangkatan();
	String getWaktu();
	String getPerusahaan();
	String getKelas();
	int getHarga();
	
	

}
