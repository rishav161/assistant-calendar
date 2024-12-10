package com.calendarassistant.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.calendarassistant.model.Meeting;
import com.calendarassistant.model.User;

public interface MeetingRepository {
    void save(Meeting meeting);
    List<Meeting> findMeetingsByUserAndDateRange(User user, LocalDateTime start, LocalDateTime end);
}