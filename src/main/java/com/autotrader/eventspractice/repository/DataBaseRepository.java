package com.autotrader.eventspractice.repository;

import com.autotrader.eventspractice.config.DataBaseConfiguration;
import com.autotrader.eventspractice.entity.Event;
import com.autotrader.eventspractice.entity.Events;
import com.autotrader.eventspractice.entity.User;
import com.autotrader.eventspractice.exceptions.WebApplicationException;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataBaseRepository {
    private final DataBaseConfiguration config;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    @Autowired
    public DataBaseRepository(DataBaseConfiguration config) {
        this.config = config;
    }

    public Event getEvent(int id) {

        try {
            connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Events WHERE id = " + id);

            if (resultSet.next()) {
                return new Event(resultSet.getLong("id"),
                        resultSet.getString("event_name"),
                        resultSet.getString("location"),
                        resultSet.getString("description"),
                        resultSet.getTimestamp("start_date_time").toLocalDateTime(),
                        resultSet.getTimestamp("end_date_time").toLocalDateTime());
            }

        } catch (SQLException e) {
            throw new WebApplicationException(HttpStatus.INTERNAL_SERVER_ERROR_500, "Unable to retrieve event");
        } finally {
            closeConnection();
        }
        return null;
    }

    public Events getEvents() {

        try {
            connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Events");

            List<Event> events = new ArrayList<>();

            while (resultSet.next()) {
                events.add(new Event(resultSet.getLong("id"),
                        resultSet.getString("event_name"),
                        resultSet.getString("location"),
                        resultSet.getString("description"),
                        resultSet.getTimestamp("start_date_time").toLocalDateTime(),
                        resultSet.getTimestamp("end_date_time").toLocalDateTime()));

            }

            return new Events(events);

        } catch (SQLException e) {
            throw new WebApplicationException(HttpStatus.INTERNAL_SERVER_ERROR_500, "Unable to retrieve event");
        } finally {
            closeConnection();
        }
    }

    public User getUser(int id) {

        try {
            connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Users WHERE id = " + id);

            if (resultSet.next()) {
                return new User(resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email_address"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new WebApplicationException(HttpStatus.INTERNAL_SERVER_ERROR_500, "Unable to retrieve user");
        } finally {
            closeConnection();
        }
        return null;
    }

    private void closeConnection() {
        try {
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
