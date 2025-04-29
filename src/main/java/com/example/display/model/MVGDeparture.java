package com.example.display.model;


public class MVGDeparture {
    private String destination;
    private String plannedDepartureTime;
    private String realtimeDepartureTime;
    private String label;
    private String source;

    public MVGDeparture(String destination, String plannedDepartureTime, String realtimeDepartureTime, String label, String source) {
        this.destination = destination;
        this.plannedDepartureTime = plannedDepartureTime;
        this.realtimeDepartureTime = realtimeDepartureTime;
        this.label = label;
        this.source = source;
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


}
