package com.autotrader.eventspractice.controller;

import com.autotrader.eventspractice.entity.Event;
import com.autotrader.eventspractice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/event")
public class EventController {

    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping(value = "{eventId}", produces = "application/json")
    @ResponseBody()
    public Event getEvent(@PathVariable(value = "eventId") int eventId) {
        return eventService.getEvent(eventId);
    }
}
