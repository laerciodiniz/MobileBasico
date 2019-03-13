package com.example.mobilebasico.database;

import android.content.Context;

import com.example.mobilebasico.model.Events;
import com.example.mobilebasico.model.Users;
import com.example.mobilebasico.ui.register.RegisterContract;
import com.example.mobilebasico.ui.register.RegisterPresenter;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.List;

public class AppDbHelper implements DbHelper{

    DbOpenHelper dbHelper;

    RuntimeExceptionDao<Users, Integer> usersDao; //= getHelper().getUsersDao();
    RuntimeExceptionDao<Events, Integer> eventsDao; //= getHelper().getEventsDao();

    public AppDbHelper(Context context) {
        dbHelper = new DbOpenHelper(context);
        usersDao = dbHelper.getRuntimeExceptionDao(Users.class);
        eventsDao = dbHelper.getRuntimeExceptionDao(Events.class);
    }

    @Override
    public List<Events> queryEvents(int userId) throws SQLException {

        List<Events> eventsList = eventsDao.queryBuilder()
                    .where()
                    .eq(Events.COL_USER_ID, userId)
                    .query();
        return eventsList;
    }

    @Override
    public Events insertEvent(Events event) {
        eventsDao.createOrUpdate(event);
        return event;
    }

    @Override
    public Users insertUser(Users user) throws SQLException {
        usersDao.create(user);
        return user;
    }

    @Override
    public List<Users> queryUserEmail(String userEmail) throws SQLException {
        return usersDao.queryBuilder()
                .where()
                .eq(Users.COL_EMAIL, userEmail)
                .query();
    }

    @Override
    public List<Users> queryValidateLogin(String userEmail, String userPassword) throws SQLException {
        return usersDao.queryBuilder()
                .where()
                .eq(Users.COL_EMAIL, userEmail)
                .and()
                .eq(Users.COL_PASSWORD, userPassword)
                .query();
    }

    @Override
    public Users queryUser(String userEmail) throws SQLException {

        Users user = (Users) usersDao.queryBuilder()
                .where()
                .eq(Users.COL_EMAIL, userEmail)
                .and()
                .eq(Users.COL_EMAIL, userEmail)
                .queryForFirst();

        return user;
    }
}
