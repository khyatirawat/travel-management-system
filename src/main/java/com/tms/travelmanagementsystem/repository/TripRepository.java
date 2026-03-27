package com.tms.travelmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tms.travelmanagementsystem.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Integer> {
}