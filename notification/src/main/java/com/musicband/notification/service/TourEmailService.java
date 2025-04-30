package com.musicband.notification.service;

import com.musicband.notification.dto.NotificationDto;
import com.musicband.notification.dto.TourMsgDto;

public interface TourEmailService {

    boolean addEmailForNotification(NotificationDto notificationDto);

    void sendNotificationAboutTour(Long tourId, String subject, String text);

    void tourUpdatedMessage(TourMsgDto tourMsgDto);

    void tourDeletedMessage(TourMsgDto tourMsgDto);

    void ticketUpdatedMessage(Long tourId);
}
