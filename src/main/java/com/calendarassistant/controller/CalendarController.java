package com.calendarassistant.controller;

import com.calendarassistant.dto.MeetingRequestDTO;
import com.calendarassistant.dto.FreeSlotsRequestDTO;
import com.calendarassistant.model.Meeting;
import com.calendarassistant.service.CalendarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/calendar")
public class CalendarController {
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarSercalvice = calendarService;
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