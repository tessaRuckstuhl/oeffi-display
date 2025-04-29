package com.example.display.model;


public class MVGDeparture {
    private String destination;
    private String plannedDepartureTime;
    private String realtimeDepartureTime;
    private String label;
    private String source;
    private Integer diffMinutes;

    public MVGDeparture(String destination, String plannedDepartureTime, String realtimeDepartureTime, String label, String source, Integer diffMinutes) {
        this.destination = destination;
        this.plannedDepartureTime = plannedDepartureTime;
        this.realtimeDepartureTime = realtimeDepartureTime;
        this.label = label;
        this.source = source;
        this.diffMinutes = diffMinutes;
    }

    public String getDestination() {
        return destination;
    }

    public String getPlannedDepartureTime() {
        return plannedDepartureTime;
    }

    public String getRealtimeDepartureTime() {
        return realtimeDepartureTime;
    }

    public String getLabel(){
        return label;
    }

    public String getSource(){
        return source;
    }

    public Integer getDiffMinutes(){
        return diffMinutes;
    }


}
