package com.musicband.notification.service.impl;

import com.musicband.notification.dto.NotificationDto;
import com.musicband.notification.dto.TourMsgDto;
import com.musicband.notification.service.impl.email.EmailService;
import com.musicband.notification.service.impl.redis.RedisService;
import com.musicband.notification.service.TourEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TourEmailServiceImpl implements TourEmailService {

    private final EmailService emailService;
    private final RedisService redisService;

    @Override
    public boolean addEmailForNotification(NotificationDto notificationDto) {
        return redisService.addEmail(notificationDto);
    }

    @Override
    public void sendNotificationAboutTour(Long tourId, String subject, String text) {
        Set<String> subscribers = redisService.getSubscribersForTour(tourId);
        subscribers.forEach(
                subscriber -> emailService.sendSimpleMail(subscriber,subject,text)
        );
    }

    @Override
    public void tourUpdatedMessage(TourMsgDto tourMsgDto) {
        sendNotificationAboutTour(tourMsgDto.tourId(),
                "Tour Updated",
                "The tour "+tourMsgDto.title()+" that you tracked has been updated.");
    }

    @Override
    public void tourDeletedMessage(TourMsgDto tourMsgDto) {
        sendNotificationAboutTour(tourMsgDto.tourId(),
                "Tour Deleted",
                "The tour "+tourMsgDto.title()+" that you tracked has been delete. Sorry:(");
    }

    @Override
    public void ticketUpdatedMessage(Long tourId) {
        sendNotificationAboutTour(tourId,
                "New Tickets of tour",
                "The tour that you tracked has adding new tickets.");
    }

}
