package com.example.mobilebasico.database;

import com.example.mobilebasico.model.Events;
import com.example.mobilebasico.model.Users;

import java.sql.SQLException;
import java.util.List;

public interface DbHelper {

    List<Events> queryEvents(int userId) throws SQLException;

    Events insertEvent(Events event);

    Users insertUser(Users user) throws SQLException;

    List<Users> queryUserEmail(String userEmail) throws SQLException;

    List<Users> queryValidateLogin(String userEmail, String userPassword) throws SQLException;
}
