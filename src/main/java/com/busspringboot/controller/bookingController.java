package com.busspringboot.controller;

import java.util.List;

import com.busspringboot.model.Booking;
import com.busspringboot.model.Keberangkatan;
import com.busspringboot.model.Penumpang;
import com.busspringboot.repository.BookingRepository;
import com.busspringboot.repository.KeberangkatanRepository;
import com.busspringboot.repository.penumpangRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class bookingController {

    @Autowired
    penumpangRepository penumpangRepo;

    @Autowired
    BookingRepository bookingRepo;

    @Autowired
    KeberangkatanRepository keberangkatanRepo;


    //untuk memnuat booking pemesanan 
	@GetMapping("/booking")
	public String getBooking(Model model){
	model.addAttribute("dataBooking",new Booking());
		return "formbooking";
	}
    //menampilkan keberhasilan pemesanan	
	@PostMapping("/pesanbooking")
	public String getBooking(@ModelAttribute("dataBooking")Booking dataBooking,
	Model model){
	long id_keberangkatan = dataBooking.getId_keberangkatan().getId();
	String nik = dataBooking.getNik().getNik();
	List<Penumpang>penumpangSementara=penumpangRepo.getByNik(nik);
	dataBooking.setNik(penumpangSementara.get(0));
	Keberangkatan keberangkatanSementara =keberangkatanRepo.getById(id_keberangkatan);
	dataBooking.setId_keberangkatan(keberangkatanSementara);
	bookingRepo.save(dataBooking);
	List<Booking> hasilSimpan = bookingRepo.findByNik(dataBooking.getNik());
	model.addAttribute("data", hasilSimpan.get(hasilSimpan.size()-1));
	return "bookingdetail2";
	}
    
}