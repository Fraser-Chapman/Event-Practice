package com.autotrader.eventspractice.service;

import com.autotrader.eventspractice.entity.User;
import com.autotrader.eventspractice.repository.DataBaseRepository;
import com.autotrader.eventspractice.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private DataBaseRepository dataBaseRepository;

    @Autowired
    public UserService(DataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
    }

    public User getUser(int id) {
        return dataBaseRepository.getUser(id);
    }
}
