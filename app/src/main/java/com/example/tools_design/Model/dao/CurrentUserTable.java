package com.example.tools_design.Model.dao;

public class CurrentUserTable {
    public static final String DB_NAME = "current_user.db";
    public static final String TABLE_NAME = "current_user_info";
    public static final String COL_NAME = "username";

    public static final String CREATE_TABLE = "create table "
            +TABLE_NAME + " ("
            +COL_NAME +" text);";
}
