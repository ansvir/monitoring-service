package com.tohant.monitoringservice.web;

import com.tohant.monitoringservice.memory.model.MonitoringResult;
import com.tohant.monitoringservice.web.model.MonitoringResultDto;
import com.tohant.monitoringservice.web.model.StatusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/rest/monitor")
public class MonitoringController {

    private final MonitoringService monitoringService;

    @Autowired
    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @PostMapping("/start")
    public StatusDto startMonitoring() {
//        try {
        monitoringService.startMonitoring();
//        } catch (ResourceUnavailableException e) {
//            return new StatusDto(false, "Cannot start the monitoring, because next exception occurred: "
//                    + e.getMessage() + ". Try to restart monitoring using /rest/monitor/start.");
//        }
        return new StatusDto(true, "Monitoring started. Use /rest/monitor/stop to stop monitoring.");
    }

    @PostMapping("/stop")
    public StatusDto stopMonitoring() {
        monitoringService.stopMonitoring();
        return new StatusDto(true, "Monitoring stopped. Use /rest/monitor/result/last to fetch " +
                "last monitoring results or /rest/monitor/start to restart the monitoring.");
    }

    @GetMapping("/result/last")
    public MonitoringResultDto getLastResults() {
        MonitoringResult result = monitoringService.getLastResults();
        MonitoringResultDto dto = new MonitoringResultDto();
        Timestamp timestamp = new Timestamp(Long.parseLong(result.getTimestamp()));
        dto.setTimestamp(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(timestamp));
        dto.setMessage(result.getAvailabilityMessage());
        return dto;
    }
}
