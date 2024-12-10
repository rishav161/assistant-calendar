package com.calendarassistant.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Meeting {
    private UUID id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<User> participants;
    private String title;

    public Meeting() {
        this.id = UUID.randomUUID();
    }

    public Meeting(LocalDateTime startTime, LocalDateTime endTime, List<User> participants, String title) {
        this.id = UUID.randomUUID();
        this.startTime = startTime;
        this.endTime = endTime;
        this.participants = participants;
        this.title = title;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    public List<User> getParticipants() { return participants; }
    public void setParticipants(List<User> participants) { this.participants = participants; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean overlaps(Meeting other) {
        return !(this.endTime.isBefore(other.startTime) || this.startTime.isAfter(other.endTime));
    }
}