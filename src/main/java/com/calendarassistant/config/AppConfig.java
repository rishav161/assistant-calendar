package com.calendarassistant.config;

import com.calendarassistant.repository.InMemoryMeetingRepository;
import com.calendarassistant.repository.InMemoryUserRepository;
import com.calendarassistant.repository.MeetingRepository;
import com.calendarassistant.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserRepository userRepository() {
        return new InMemoryUserRepository();
    }

    @Bean
    public MeetingRepository meetingRepository() {
        return new InMemoryMeetingRepository();
    }
}