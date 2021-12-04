package com.busspringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.busspringboot.model.Penumpang;

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

	// untuk menu awal
	@GetMapping("/bustrapel")
	public String getbustrapel() {
		return "index";
	}

	@GetMapping("blog")
	public String getblog() {
		return "blog";
	}

	@GetMapping("prokescovid")
	public String getprokescovid() {
		return "prokescovid";
	}

	// untuk login penumpang
	@GetMapping("/loginpenumpang")
	public String getForm(Model model) {
		model.addAttribute("penumpangData", new Penumpang());
		return "formlogin";
	}

	// untuk mencheck penumpang
	@RequestMapping("/checkpenumpang")
	public String getPenumpang(@ModelAttribute("penumpangData") Penumpang penumpangData, Model model) {
		String nik = penumpangData.getNik();
		List<Penumpang> hasil = penumpangRepo.findByNik(nik);
		String output;
		if (hasil.size() == 0) {
			output = "kenihilan";
		} else {
			penumpangRepo.findByNik(nik);
			model.addAttribute("penumpangData", hasil);
			output = "detailpenumpang";
		}
		return output;
	}

	// untuk daftar penumpang
	@GetMapping("/daftar")
	public String getFormPenumpang(Model model) {
		model.addAttribute("penumpangData", new Penumpang());
		return "formpenumpangbaru";
	}

	// untuk daftar penumpang
	@PostMapping("/createpenumpang")
	public String createPenumpang(@ModelAttribute("penumpangData") Penumpang penumpangData, Model model) {
		// String nik =penumpangData.getNik();
		// List<Penumpang> hasil= penumpangRepo.findByNik(nik);
		penumpangRepo.save(penumpangData);
		// model.addAttribute("data", hasil);
		return "detailpenumpang";
	}

}
