package com.calendarassistant.dto;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import com.calendarassistant.model.User;

public class FreeSlotsRequestDTO {
    private List<User> users;
    private Duration meetingDuration;
    private LocalDateTime start;
    private LocalDateTime end;

    // Getters and Setters
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Duration getMeetingDuration() {
        return meetingDuration;
    }

    public void setMeetingDuration(Duration meetingDuration) {
        this.meetingDuration = meetingDuration;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
