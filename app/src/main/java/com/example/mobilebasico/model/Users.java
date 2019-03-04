package com.example.mobilebasico.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = Users.TABLE_NAME) //@DatabaseTable ou @Entity para definir a classe como tabela
public class Users {

    public static final String TABLE_NAME = "users";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASSWORD = "password";

    //@DatabaseField ou @Column para definir as variaveis como campos
    @DatabaseField (generatedId = true, columnName = COL_ID)
    private int id;

    @DatabaseField (columnName = COL_NAME)
    private String name;

    @DatabaseField (columnName = COL_EMAIL)
    private String email;

    @DatabaseField (columnName = COL_PASSWORD)
    private String password;

    public Users() {
    }

    public Users(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
