package com.tms.travelmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tms.travelmanagementsystem.model.Destination;

public interface DestinationRepository extends JpaRepository<Destination, Long> {

    List<Destination> findByPriceLessThanEqual(int price);

}