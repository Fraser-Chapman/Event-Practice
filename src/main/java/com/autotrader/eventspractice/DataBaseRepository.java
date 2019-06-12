package com.autotrader.eventspractice;

import com.autotrader.eventspractice.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

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
            System.out.println("oops");
            e.printStackTrace();
        }
        finally {
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

        return null;

    }
}
