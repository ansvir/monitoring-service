package com.tohant.monitoringservice.memory.model;

public class MonitoringResult {

    private String timestamp;
    private String availabilityMessage;

    public MonitoringResult() {
    }

    public MonitoringResult(String timestamp, String availabilityMessage) {
        this.timestamp = timestamp;
        this.availabilityMessage = availabilityMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAvailabilityMessage() {
        return availabilityMessage;
    }

    public void setAvailabilityMessage(String availabilityMessage) {
        this.availabilityMessage = availabilityMessage;
    }
}
