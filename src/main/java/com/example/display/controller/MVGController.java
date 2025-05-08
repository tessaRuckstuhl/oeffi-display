package com.example.display.controller;

import com.example.display.model.MVGDeparture;
import com.example.display.service.MVGService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class MVGController {
    private final MVGService mvgService;
    public MVGController(MVGService mvgService) {
        this.mvgService = mvgService;
    }
    @GetMapping("/")
    public String getMVG(Model model) {
//        return mvgService.getDepartures();
        List<MVGDeparture> departures = mvgService.getDepartures();
        model.addAttribute("departures", departures);

        LocalDateTime now = LocalDateTime.now();
        String lastUpdated = now.getHour() + ":" + now.getMinute();
        model.addAttribute("lastUpdated", lastUpdated);
        return "index";
    }

}
