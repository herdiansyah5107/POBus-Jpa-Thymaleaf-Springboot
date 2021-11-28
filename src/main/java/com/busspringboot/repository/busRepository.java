package com.busspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.busspringboot.model.Bus;

public interface busRepository extends JpaRepository<Bus, String> {

}
