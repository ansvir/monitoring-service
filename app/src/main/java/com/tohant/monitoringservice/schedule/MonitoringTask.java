package com.tohant.monitoringservice.schedule;

import com.tohant.monitoringservice.mail.EmailService;
import com.tohant.monitoringservice.mail.model.MonitoringMail;
import com.tohant.monitoringservice.memory.MonitoringRepository;
import com.tohant.monitoringservice.memory.model.MonitoringResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

import static com.tohant.monitoringservice.mail.model.ResourceAvailabilityType.RESOURCE_AVAILABLE;
import static com.tohant.monitoringservice.mail.model.ResourceAvailabilityType.RESOURCE_UNAVAILABLE;

@Component
public class MonitoringTask implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringTask.class);

    @Value("${default.url}")
    private String url;

    @Value("${default.timeout}")
    private String timeout;

    private final MonitoringRepository monitoringRepository;
    private final EmailService emailService;

    @Autowired
    public MonitoringTask(MonitoringRepository monitoringRepository, EmailService emailService) {
        this.monitoringRepository = monitoringRepository;
        this.emailService = emailService;
    }

    @Override
    public void run() {
        logger.info("Starting monitoring task...");
        MonitoringResult result = new MonitoringResult();
        boolean available = false;
        try {
            available = InetAddress.getByName(url).isReachable(Integer.parseInt(timeout));
        } catch (IOException e) {
            String timestamp = String.valueOf(System.currentTimeMillis());
            result.setTimestamp(timestamp);
            result.setAvailabilityMessage("Specified resource cannot be reached: " + e.getMessage());
            monitoringRepository.put(result);
            emailService.sendEmail(new MonitoringMail(url, RESOURCE_UNAVAILABLE, timestamp));
        }
        String timestamp = String.valueOf(System.currentTimeMillis());
        result.setTimestamp(timestamp);
        if (available) {
            result.setAvailabilityMessage("Resource is available.");
            emailService.sendEmail(new MonitoringMail(url, RESOURCE_AVAILABLE, timestamp));
        } else {
            result.setAvailabilityMessage("Resource is unavailable.");
            emailService.sendEmail(new MonitoringMail(url, RESOURCE_UNAVAILABLE, timestamp));
        }
        monitoringRepository.put(result);
        logger.info("Task was successfully executed.");
    }
}
