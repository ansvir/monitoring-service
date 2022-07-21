package com.tohant.monitoringservice.mail.model;

public enum ResourceAvailabilityType {

    RESOURCE_AVAILABLE("Congratulations! Resource is available!"),
    RESOURCE_UNAVAILABLE("Resource is unavailable!");

    private final String text;

    ResourceAvailabilityType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
