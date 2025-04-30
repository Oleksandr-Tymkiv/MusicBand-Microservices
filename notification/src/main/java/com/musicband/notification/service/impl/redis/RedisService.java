package com.musicband.notification.service.impl.redis;

import com.musicband.notification.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    public boolean addEmail(NotificationDto notificationDto) {
        boolean isEmailAdded = false;
        if(!isEmailSubscribed(notificationDto)) {
            redisTemplate.opsForSet().add(getKeyForTour(notificationDto.getTourId()), notificationDto.getEmail());
            isEmailAdded = true;
        }
        return isEmailAdded;
    }

    private String getKeyForTour(Long tourId){
        return "tour_tracking:"+tourId;
    }

    public Set<String> getSubscribersForTour(Long tourId) {
        return redisTemplate.opsForSet().members(getKeyForTour(tourId));
    }

    private boolean isEmailSubscribed(NotificationDto notificationDto) {
        return Boolean.TRUE.equals(
                redisTemplate.opsForSet().isMember(getKeyForTour(notificationDto.getTourId()), notificationDto.getEmail())
        );
    }
}
