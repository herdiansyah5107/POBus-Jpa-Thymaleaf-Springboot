package com.busspringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.busspringboot.model.Penumpang;


public interface penumpangRepository extends JpaRepository<Penumpang, String>{
	

	List<Penumpang> findByNik(String hayolo);






}
