package com.busspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.busspringboot.model.Booking;
import com.busspringboot.model.BookingDetail;

import com.busspringboot.model.Keberangkatan;
import com.busspringboot.model.Keberangkatandetail;
import com.busspringboot.model.Penumpang;
import com.busspringboot.model.cariKeberangkatan;
import com.busspringboot.repository.BookingRepository;
import com.busspringboot.repository.KeberangkatanRepository;
import com.busspringboot.repository.busRepository;
import com.busspringboot.repository.penumpangRepository;

@Controller

public class busController {

	@Autowired
	busRepository busRepo;
	
	@Autowired
	penumpangRepository penumpangRepo;
	
	@Autowired
	KeberangkatanRepository keberangkatanRepo;
	
	@Autowired
	BookingRepository bookingRepo;
	
	
	//untuk login penumpang
	@GetMapping("/loginpenumpang")
	public String getForm(Model model){
		model.addAttribute("penumpangData",new Penumpang());
		return "formlogin";
	}
	//untuk mencheck penumpang
	@RequestMapping("/checkpenumpang")
	public String getPenumpang (@ModelAttribute("penumpangData")
	Penumpang penumpangData,Model model){
		String nik =penumpangData.getNik();
		List<Penumpang> hasil= penumpangRepo.findByNik(nik);	
		String output;
		if(hasil.size()==0) {
			output = "kenihilan";
		}else {
		penumpangRepo.findByNik(nik);		
		model.addAttribute("data", hasil);	
		output= "detailpenumpang";
		}
		return output;
	}
	
//untuk daftar penumpang
	@GetMapping("/daftar")
	public String getFormPenumpang(Model model){
		model.addAttribute("formData", new Penumpang());
		return "formpenumpangbaru";
	}
//untuk daftar penumpang
	@PostMapping("/createpenumpang")
	public String createPenumpang(@ModelAttribute("formData")Penumpang formData){
	penumpangRepo.save(formData);
	return "redirect:/daftar";
	}
	
//untuk mencari keberangkatan
	@GetMapping("/carikeberangkatan")
	public String getKeberangkatan(Model model) {
	model.addAttribute("formBerangkat", new cariKeberangkatan() );
	return "carikeberangkatan";
	}
//untuk mencari detail keberangkatan setelah formBerangkat
	@RequestMapping("/checkkeberangkatan")
	public String getBerangkat(@ModelAttribute("formBerangkat")
	cariKeberangkatan formBerangkat, Model model) {
	String tanggal = formBerangkat.getTanggal();
	String terminal_awal = formBerangkat.getTerminal();
	List<Keberangkatandetail> hasil=keberangkatanRepo.getDetail(terminal_awal,tanggal);
	String output;
		if(hasil.size()==0) {
		output = "kenihilankeberangkatan";	
		}else {
		keberangkatanRepo.getDetail(terminal_awal,tanggal);	
		model.addAttribute("data", hasil);	
		output= "listdetailkeberangkatan";
		}
		return output;
		}

//untuk memnuat booking pemesanan
	@GetMapping("/booking")
	public String getBooking(Model model){
	model.addAttribute("dataBooking",new Booking());
		return "formbooking";
	}
	@PostMapping("/pesanbooking")
	public String getBooking(@ModelAttribute("dataBooking")Booking dataBooking,
	Model model){
	long id_keberangkatan = dataBooking.getId_keberangkatan().getId();
	String nik = dataBooking.getNik().getNik();	
	bookingRepo.save(dataBooking);
	List<BookingDetail> hasil = bookingRepo.getDetail(id_keberangkatan,nik);
	
	model.addAttribute("data",hasil);
	return "bookingdetail";
	}
//Membatalkan booking
	@GetMapping("/cancel")
	public String cancelBooking(Model model) {
	model.addAttribute("dataBooking", new Booking());
	return "formcancel";
		}
	

	@PostMapping("/cancelBooking")
	public String BookingCancel(@ModelAttribute("dataBooking")Booking dataBooking,
	Model model){
	long id = dataBooking.getId(); 
	bookingRepo.deleteById(id);
	return "cancelmassage";


	}
}

	

