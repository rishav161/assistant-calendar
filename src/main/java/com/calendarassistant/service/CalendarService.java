package com.calendarassistant.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.calendarassistant.model.Meeting;
import com.calendarassistant.model.User; // Import added
import com.calendarassistant.repository.MeetingRepository;
import com.calendarassistant.repository.UserRepository;

@Service
public class CalendarService {
    private final MeetingRepository meetingRepository;
    private final UserRepository userRepository; // Keep if needed

    public CalendarService(MeetingRepository meetingRepository, UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.userRepository = userRepository; // Keep if needed
    }

    public boolean scheduleMeeting(Meeting meeting) {
        for (User participant : meeting.getParticipants()) {
            List<Meeting> existingMeetings = meetingRepository.findMeetingsByUserAndDateRange(
                participant, 
                meeting.getStartTime(), 
                meeting.getEndTime()
            );

            if (!existingMeetings.isEmpty()) {
                return false; // Conflict exists
            }
        }

        meetingRepository.save(meeting);
        return true;
    }

    public List<LocalDateTime> findCommonFreeSlots(
        List<User> users, 
        Duration meetingDuration, 
        LocalDateTime start, 
        LocalDateTime end
    ) {
        List<List<Meeting>> userMeetings = users.stream()
            .map(user -> meetingRepository.findMeetingsByUserAndDateRange(user, start, end))
            .collect(Collectors.toList());

        return findFreeSlots(userMeetings, meetingDuration, start, end);
    }

    private List<LocalDateTime> findFreeSlots(
        List<List<Meeting>> userMeetings, 
        Duration meetingDuration, 
        LocalDateTime start, 
        LocalDateTime end
    ) {
        List<LocalDateTime> freeSlots = new ArrayList<>(); // Fixed
        LocalDateTime currentTime = start;

        while (currentTime.plus(meetingDuration).isBefore(end)) {
            final LocalDateTime checkTime = currentTime;
            boolean isSlotFree = userMeetings.stream()
                .allMatch(meetings -> 
                    meetings.stream().noneMatch(meeting -> 
                        isTimeSlotOverlap(checkTime, checkTime.plus(meetingDuration), meeting)
                    )
                );

            if (isSlotFree) {
                freeSlots.add(currentTime);
            }

            currentTime = currentTime.plus(Duration.ofMinutes(30));
        }

        return freeSlots;
    }

    private boolean isTimeSlotOverlap(
        LocalDateTime proposedStart, 
        LocalDateTime proposedEnd, 
        Meeting existingMeeting
    ) {
        return !(proposedEnd.isBefore(existingMeeting.getStartTime()) || 
                 proposedStart.isAfter(existingMeeting.getEndTime()));
    }
}
