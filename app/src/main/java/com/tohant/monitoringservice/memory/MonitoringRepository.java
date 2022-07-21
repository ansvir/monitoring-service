package com.tohant.monitoringservice.memory;

import com.tohant.monitoringservice.memory.model.MonitoringResult;

public interface MonitoringRepository {
    MonitoringResult findLast();
    void put(MonitoringResult monitoringResult);
}
