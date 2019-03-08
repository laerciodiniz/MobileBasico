package com.example.mobilebasico.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mobilebasico.model.Events;
import com.example.mobilebasico.model.Users;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DbOpenHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DbOpenHelper.class.getSimpleName();

    public static final String DATABASE_NAME = "mobilebasico";
    public static final int DATABASE_VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {

            TableUtils.createTable(connectionSource, Users.class);
            TableUtils.createTable(connectionSource, Events.class);

        } catch (SQLException e) {
            Log.e(TAG, "Banco de dados não foi criado", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {

    }

    //Objeto DAO para poder ter acesso aos dados dos Usuarios
    private Dao<Users, Integer> mUsersDao = null;

    public Dao<Users, Integer> getUsersDao() throws SQLException {
        if ( mUsersDao == null ){
            mUsersDao = getDao(Users.class);
        }
        return mUsersDao;
    }

    //Objeto DAO para poder ter acesso aos dados dos Eventos
    private Dao<Events, Integer> mEventsDao = null;

    public Dao<Events, Integer> getEventsDao() throws SQLException {
        if ( mEventsDao == null ){
            mEventsDao = getDao(Events.class);
        }
        return mEventsDao;
    }

    @Override
    public void close() {
        mUsersDao = null;
        mEventsDao = null;
        super.close();
    }
}
