package com.example.mobilebasico.database;

import com.example.mobilebasico.model.Events;
import com.example.mobilebasico.model.Users;

import java.sql.SQLException;
import java.util.List;

public class AppDbHelper implements DbHelper{

    private final DbOpenHelper dbHelper;

    public AppDbHelper(DbOpenHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public List<Events> queryEvents(int userId) throws SQLException {
        /*QueryBuilder <Events, Integer> queryBuilder = dbHelper.getEventsDao().queryBuilder();
        queryBuilder.where().eq(Events.COL_USER_ID, userId);
        PreparedQuery<Events> preparedQuery = queryBuilder.prepare();
        List<Events> eventsList = dbHelper.getEventsDao().query(preparedQuery);
        return eventsList;*/

        List<Events> eventsList = null;
            eventsList = dbHelper.getEventsDao()
                    .queryBuilder()
                    .where()
                    .eq(Events.COL_USER_ID, userId)
                    .query();
        return eventsList;
    }

    @Override
    public Events insertEvent(Events event) {
        dbHelper.getEventsDao().createOrUpdate(event);
        return event;
    }

    @Override
    public Users insertUser(Users user) throws SQLException {
        dbHelper.getUsersDao().create(user);
        return user;
    }

    @Override
    public Users queryUserName(String userName) throws SQLException {
        return (Users) dbHelper.getUsersDao()
                .queryBuilder()
                .where()
                .eq(Users.COL_NAME, userName)
                .query();
    }
}
