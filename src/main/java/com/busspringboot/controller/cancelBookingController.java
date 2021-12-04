package com.busspringboot.controller;

import com.busspringboot.model.Booking;
import com.busspringboot.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class cancelBookingController {
    
    @Autowired
    BookingRepository bookingRepo;
    
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
