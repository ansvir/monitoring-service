package com.tohant.monitoringservice.mail.model;

public class MonitoringMail {

    private String targetUrl;
    private ResourceAvailabilityType verdict;
    private String reportTimestamp;

    public MonitoringMail(String targetUrl, ResourceAvailabilityType verdict, String reportTimestamp) {
        this.targetUrl = targetUrl;
        this.verdict = verdict;
        this.reportTimestamp = reportTimestamp;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public ResourceAvailabilityType getVerdict() {
        return verdict;
    }

    public void setVerdict(ResourceAvailabilityType verdict) {
        this.verdict = verdict;
    }

    public String getReportTimestamp() {
        return reportTimestamp;
    }

    public void setReportTimestamp(String reportTimestamp) {
        this.reportTimestamp = reportTimestamp;
    }

    public String getReport() {
        StringBuilder builder = new StringBuilder();
        builder.append("Monitoring results report, related to resource \"");
        builder.append(getTargetUrl());
        builder.append("\".\n");
        builder.append("Verdict - ");
        builder.append(getVerdict().getText());
        builder.append(".\n");
        builder.append("Report time: ");
        builder.append(getReportTimestamp());
        return builder.toString();
    }
}
