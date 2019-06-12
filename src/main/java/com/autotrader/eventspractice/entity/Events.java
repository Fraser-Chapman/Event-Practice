package com.autotrader.eventspractice.entity;

import java.util.List;

public class Events {

    private List<Event> events;

    public Events(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }
}
