package com.autotrader.eventspractice.service;

import com.autotrader.eventspractice.entity.Event;
import com.autotrader.eventspractice.entity.Events;
import com.autotrader.eventspractice.repository.DataBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private DataBaseRepository dataBaseRepository;

    @Autowired
    public EventService(DataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
    }

    public Event getEvent(int id) {
        return dataBaseRepository.getEvent(id);
    }

    public Events getEvents() {
        return dataBaseRepository.getEvents();
    }
}
