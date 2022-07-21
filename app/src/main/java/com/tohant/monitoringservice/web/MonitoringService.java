package com.tohant.monitoringservice.web;

import com.tohant.monitoringservice.memory.MonitoringRepository;
import com.tohant.monitoringservice.memory.model.MonitoringResult;
import com.tohant.monitoringservice.schedule.MonitoringTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledFuture;

@Service
public class MonitoringService {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);

    @Value("${default.rate}")
    private String rate;

    private final TaskScheduler taskScheduler;
    private ScheduledFuture scheduledFuture;
    private final MonitoringTask task;
    private final MonitoringRepository monitoringRepository;

    @Autowired
    public MonitoringService(TaskScheduler taskScheduler, MonitoringTask task,
             MonitoringRepository monitoringRepository) {
        this.taskScheduler = taskScheduler;
        this.task = task;
        this.monitoringRepository = monitoringRepository;
    }

    public void startMonitoring() {
        scheduledFuture = taskScheduler.schedule(task, new CronTrigger(rate));
        logger.info("Monitoring was started.");
    }

    public void stopMonitoring() {
        scheduledFuture.cancel(false);
        logger.info("Monitoring was stopped.");
    }

    public MonitoringResult getLastResults() {
        return monitoringRepository.findLast();
    }
}
