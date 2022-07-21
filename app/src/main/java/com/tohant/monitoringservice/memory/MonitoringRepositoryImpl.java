package com.tohant.monitoringservice.memory;

import com.tohant.monitoringservice.memory.model.MonitoringResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class MonitoringRepositoryImpl implements MonitoringRepository {

    private final static String KEY = "Monitor";

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, String> hashOperations;

    @Autowired
    public MonitoringRepositoryImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public MonitoringResult findLast() {
        long id = hashOperations.entries(KEY).keySet().stream().mapToLong(Long::parseLong).max().getAsLong();
        String message = hashOperations.get(KEY, Long.toString(id));
        MonitoringResult result = new MonitoringResult();
        result.setTimestamp(Long.toString(id));
        result.setAvailabilityMessage(message);
        return result;
    }

    @Override
    public void put(MonitoringResult monitoringResult) {
        hashOperations.put(KEY, monitoringResult.getTimestamp(), monitoringResult.getAvailabilityMessage());
    }
}
