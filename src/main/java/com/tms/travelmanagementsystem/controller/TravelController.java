package com.tms.travelmanagementsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tms.travelmanagementsystem.model.Destination;
import com.tms.travelmanagementsystem.repository.DestinationRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TravelController {

    @Autowired
    private DestinationRepository destRepo;

    @GetMapping("/travel-plan")
    public Map<String, Object> getTripPlan(@RequestParam int days, @RequestParam double budget) {
        Map<String, Object> result = new HashMap<>();

        List<Destination> possibleDestinations = destRepo.findAll()
            .stream()
            .filter(d -> d.getPrice() <= budget)
            .toList();

        if (possibleDestinations.isEmpty()) {
            result.put("message", "No destinations available within your budget.");
            return result;
        }

        Destination recommended = possibleDestinations.get(possibleDestinations.size() - 1);

        double hotel = budget * 0.4;
        double food = budget * 0.3;
        double travel = budget * 0.2;
        double activities = budget * 0.1;

        result.put("destination", recommended.getLocation());
        result.put("destinationPrice", recommended.getPrice());
        result.put("hotelTotal", hotel);
        result.put("hotelPerDay", hotel / days);
        result.put("foodTotal", food);
        result.put("foodPerDay", food / days);
        result.put("travelTotal", travel);
        result.put("travelPerDay", travel / days);
        result.put("activitiesTotal", activities);
        result.put("activitiesPerDay", activities / days);

        return result;
    }
}