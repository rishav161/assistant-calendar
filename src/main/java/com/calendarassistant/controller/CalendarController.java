package com.calendarassistant.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.calendarassistant.dto.FreeSlotsRequestDTO;
import com.calendarassistant.dto.MeetingRequestDTO;
import com.calendarassistant.model.Meeting;
import com.calendarassistant.service.CalendarService;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/schedule-meeting")
    public ResponseEntity<String> scheduleMeeting(@RequestBody MeetingRequestDTO meetingRequest) {
        Meeting meeting = new Meeting(
            meetingRequest.getStartTime(),
            meetingRequest.getEndTime(),
            meetingRequest.getParticipants(),
            meetingRequest.getTitle()
        );

        boolean scheduled = calendarService.scheduleMeeting(meeting);
        return scheduled 
            ? ResponseEntity.ok("Meeting scheduled successfully")
            : ResponseEntity.badRequest().body("Meeting conflicts with existing schedules");
    }

    @PostMapping("/find-free-slots")
    public ResponseEntity<List<LocalDateTime>> findFreeSlots(
        @RequestBody FreeSlotsRequestDTO freeSlotsRequest
    ) {
        List<LocalDateTime> freeSlots = calendarService.findCommonFreeSlots(
            freeSlotsRequest.getUsers(),
            freeSlotsRequest.getMeetingDuration(),
            freeSlotsRequest.getStart(),
            freeSlotsRequest.getEnd()
        );
        return ResponseEntity.ok(freeSlots);
    }
}