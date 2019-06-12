package com.autotrader.eventspractice;

import com.autotrader.eventspractice.entity.Event;
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
}
