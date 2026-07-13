package com.scalar.customnotificationstarter.service;

import org.springframework.stereotype.Service;


public class SmsNotificationImpl implements NotificationService{

        @Override
        public void sendNotification() {
            System.out.println("Notification sent through sms");
        }

        @Override
        public void getNotificationCount() {
            System.out.println("Notification count sms");
        }
    }

