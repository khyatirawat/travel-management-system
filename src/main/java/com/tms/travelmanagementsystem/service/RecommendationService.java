package com.tms.travelmanagementsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.travelmanagementsystem.model.Destination;
import com.tms.travelmanagementsystem.repository.DestinationRepository;

@Service
public class RecommendationService {

    @Autowired
    private DestinationRepository repo;

    public List<Destination> recommend(int budget) {
    return repo.findByPriceLessThanEqual(budget);
}
}