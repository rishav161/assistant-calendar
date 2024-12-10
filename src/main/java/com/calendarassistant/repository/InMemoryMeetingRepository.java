package com.calendarassistant.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.calendarassistant.model.Meeting;
import com.calendarassistant.model.User;

public class InMemoryMeetingRepository implements MeetingRepository {
    private final List<Meeting> meetings = new ArrayList<>();

    @Override
    public void save(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public List<Meeting> findMeetingsByUserAndDateRange(
        User user, 
        LocalDateTime start, 
        LocalDateTime end
    ) {
        return meetings.stream()
            .filter(meeting -> meeting.getParticipants().contains(user))
            .filter(meeting -> 
                !meeting.getEndTime().isBefore(start) && 
                !meeting.getStartTime().isAfter(end)
            )
            .collect(Collectors.toList());
    }
}