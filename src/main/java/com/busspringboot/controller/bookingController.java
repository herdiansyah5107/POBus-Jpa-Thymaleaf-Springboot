package com.busspringboot.controller;

import java.util.List;

import com.busspringboot.model.Booking;


import com.busspringboot.model.Keberangkatan;
import com.busspringboot.model.Keberangkatandetail;
import com.busspringboot.model.KursiKosong;
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
	@GetMapping("/formbooking")
	public String getBooking(Model model){
	model.addAttribute("dataBooking",new Booking());
	List<KursiKosong>keberangkatan=keberangkatanRepo.getAll();
	model.addAttribute("data",keberangkatan);
	return "formbooking";
	}
     //menampilkan keberhasilan pemesanan	
	 @PostMapping("/pesanbooking")
	 public String getBooking(@ModelAttribute("dataBooking")Booking dataBooking,
	 Model model){
	 long id_keberangkatan = dataBooking.getId_keberangkatan().getId();
	 String nik = dataBooking.getNik().getNik();
	 List<Penumpang>penumpangSementara=penumpangRepo.getByNik(nik);
	 List<KursiKosong>fullTank = keberangkatanRepo.getKeberangkatanFull(id_keberangkatan);
	 if(penumpangSementara.size()==0){
	 	return "kenihilan";
	 }else if(fullTank.size() != 0){
		return "kenihilankeberangkatan";
	 }else	{
	 dataBooking.setNik(penumpangSementara.get(0));
	 Keberangkatan keberangkatanSementara =keberangkatanRepo.getById(id_keberangkatan);
	 dataBooking.setId_keberangkatan(keberangkatanSementara);
	 bookingRepo.save(dataBooking);
	 List<Booking> hasilSimpan = bookingRepo.findByNik(dataBooking.getNik());
	 model.addAttribute("data", hasilSimpan.get(hasilSimpan.size()-1));
	 return "bookingdetail2";
	 }
	}
	
	//untuk mencari keberangkatan
	@GetMapping("/carikeberangkatan")
	public String getKeberangkatan(Model model) {
	model.addAttribute("dataBooking",new Booking());
	List<KursiKosong>keberangkatan=keberangkatanRepo.getAll();
	model.addAttribute("data",keberangkatan);
	model.addAttribute("formBerangkat", new Keberangkatan() );

	return "carikeberangkatan";
	}

	//untuk mencari detail keberangkatan setelah formBerangkat
	@PostMapping("/checkkeberangkatan")
	public String getBerangkat(@ModelAttribute("formBerangkat")
	Keberangkatan formBerangkat, Model model) {
	String tanggal = formBerangkat.getTanggal();
	String terminal_awal = formBerangkat.getId_jurusan().getTerminal_awal();
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

    
}
