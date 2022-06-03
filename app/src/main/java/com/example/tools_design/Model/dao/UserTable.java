package com.example.tools_design.Model.dao;

public class UserTable {
    public static final String DB_NAME = "user_information.db";
    public static final String TABLE_NAME = "user_info";
    public static final String COL_NAME = "username";
    public static final String COL_NICKNAME = "nickname";
    public static final String COL_PASSWORD = "password";
    public static final String COL_IS_ONLINE = "isOnline";

    public static final String CREATE_TABLE = "create table "
            +TABLE_NAME +" ("
            + COL_NAME + " text primary key,"
            + COL_NICKNAME + " text,"
            + COL_PASSWORD + " text,"
            + COL_IS_ONLINE +" integer);";
}
