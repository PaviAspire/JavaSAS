package com.scalar.customnotificationstarter.autoconfig;

import com.scalar.customnotificationstarter.service.EmailNotificationImpl;
import com.scalar.customnotificationstarter.service.NotificationService;
import com.scalar.customnotificationstarter.service.SmsNotificationImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnMissingBean(value=NotificationService.class)
@ConditionalOnClass(name="com.scalar.customnotificationstarter.service.NotificationService.class")
@ConditionalOnProperty(name="notfication.enabled",havingValue = "true")
public class NotificationConfig {

    @Bean
    @ConditionalOnProperty(name="notification.type",havingValue = "email",matchIfMissing = true)
    public NotificationService emailNotification(){
        System.out.println("Email Notification");
        return new EmailNotificationImpl();
    }

    @Bean
    @ConditionalOnProperty(name="notification.type",havingValue = "sms")
    public NotificationService smsNotification(){
        System.out.println("sms Notification");
        return new SmsNotificationImpl();
    }
}
