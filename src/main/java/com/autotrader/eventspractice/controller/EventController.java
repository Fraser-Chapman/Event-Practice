package com.autotrader.eventspractice.controller;

import com.autotrader.eventspractice.entity.Event;
import com.autotrader.eventspractice.entity.Events;
import com.autotrader.eventspractice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping(value = "/api/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(path = "{eventId}", produces = "application/json")
    @ResponseBody
    public Event getEvent(@PathVariable(value = "eventId") int eventId) {
        return eventService.getEvent(eventId);
    }

    @GetMapping(path = "/", produces = "application/json")
    @ResponseBody
    public Events getEvents() {
        return eventService.getEvents();
    }
}
