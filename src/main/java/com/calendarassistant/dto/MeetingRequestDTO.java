package com.calendarassistant.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.calendarassistant.model.User;

public class MeetingRequestDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<User> participants;
    private String title;

    // Getters and Setters
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public List<User> getParticipants() { return participants; }
    public void setParticipants(List<User> participants) { this.participants = participants; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}