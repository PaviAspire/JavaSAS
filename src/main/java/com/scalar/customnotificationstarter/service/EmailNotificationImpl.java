package com.scalar.customnotificationstarter.service;

import org.springframework.stereotype.Service;


public class EmailNotificationImpl implements NotificationService{

    @Override
    public void sendNotification() {
        System.out.println("Notification sent through email");
    }

    @Override
    public void getNotificationCount() {
        System.out.println("Notification count email");
    }
}
