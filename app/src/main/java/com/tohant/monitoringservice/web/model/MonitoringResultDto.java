package com.tohant.monitoringservice.web.model;

public class MonitoringResultDto {

    private String timestamp;
    private String message;

    public MonitoringResultDto() {
    }

    public MonitoringResultDto(String timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
