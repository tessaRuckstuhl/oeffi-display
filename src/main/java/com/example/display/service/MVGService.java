package com.example.display.service;

import com.example.display.model.MVGDeparture;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MVGService {

    Logger logger = LoggerFactory.getLogger(MVGService.class);

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<MVGDeparture> getDepartures(){
        List<MVGDeparture> allDepartures = new  ArrayList<>();
        allDepartures.addAll(getBusDepartures());
        allDepartures.addAll(getOtherDepartures());
        // Sort the list by planned departure time
        return allDepartures.stream()
                .sorted((d1, d2) -> d1.getPlannedDepartureTime().compareTo(d2.getPlannedDepartureTime()))
                .collect(Collectors.toList());
    }
    private List<MVGDeparture> getOtherDepartures() {
        String url = "https://www.mvg.de/api/bgw-pt/v3/departures?globalId=de:09162:64&limit=10&transportTypes=UBAHN,REGIONAL_BUS,BUS,TRAM,SBAHN";
        String jsonResponse = restTemplate.getForObject(url, String.class);
        String source = "Barthstraße";
        return processDepartures(jsonResponse, source);
    }

    private List<MVGDeparture> getBusDepartures(){
        String url = "https://www.mvg.de/api/bgw-pt/v3/departures?globalId=de:09162:65&limit=10&transportTypes=UBAHN,REGIONAL_BUS,BUS,SBAHN";
        String jsonResponse = restTemplate.getForObject(url, String.class);
        String source = "Trappentreustraße";
        return processDepartures(jsonResponse, source);
    }

    private List<MVGDeparture> processDepartures(String jsonResponse, String source){
        List<MVGDeparture> departures = new ArrayList<>();
        try {
            JsonNode departuresNode = objectMapper.readTree(jsonResponse);

            for (JsonNode dep : departuresNode) {

//                logger.info("Departure JSON Node: {}", dep.toPrettyString());
                String destination = dep.path("destination").asText();
                String plannedDepartureTimeRaw = dep.path("plannedDepartureTime").asText();
                String realtimeDepartureTimeRaw = dep.path("realtimeDepartureTime").asText();
                String label = dep.path("label").asText();

                String plannedFormattedTime = formatTime(plannedDepartureTimeRaw);
                String realtimeFormattedTime = formatTime(realtimeDepartureTimeRaw);

                departures.add(new MVGDeparture(destination, plannedFormattedTime, realtimeFormattedTime, label, source));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return departures;
    }

    private String formatTime(String timestamp) {
        long timestampMillis = Long.parseLong(timestamp);
        Instant instant = Instant.ofEpochMilli(timestampMillis);
        return DateTimeFormatter.ofPattern("HH:mm")
                .withZone(ZoneId.of("Europe/Berlin"))
                .format(instant);
    }
}

