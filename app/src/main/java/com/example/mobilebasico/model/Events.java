package com.example.mobilebasico.model;

import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = Events.TABLE_NAME)
public class Events {

    public static final String TABLE_NAME = "events";
    public static final String COL_ID = "id";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_DATE_CREATE = "date_create";
    public static final String COL_DATE_EVENT = "date_event";
    public static final String COL_USER_ID = "user_id";

    @DatabaseField (generatedId = true, columnName = COL_ID)
    private int id;

    @DatabaseField (columnName = COL_DESCRIPTION)
    private String description;

    @DatabaseField (columnName = COL_DATE_CREATE)
    private String date_create;

    @DatabaseField (columnName = COL_DATE_EVENT)
    private String date_event;

    //Chave estrangeira da tabela Users
    @DatabaseField (columnName = COL_USER_ID, foreign = true)
    private Users user_id;

    public Events() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_create() {
        return date_create;
    }

    public void setDate_create(String date_create) {
        this.date_create = date_create;
    }

    public String getDate_event() {
        return date_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }
}
