package com.example.tools_design.Model.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.tools_design.Model.bean.CurrentUserInfo;
import com.example.tools_design.Model.db.CurrentUserDB;

public class CurrentUserDao {

    private final CurrentUserDB mHelper;
    private CurrentUserInfo currentUserInfo;

    public CurrentUserDao(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        mHelper = new CurrentUserDB(context, name, factory, version);
    }

    @SuppressLint("Range")
    public CurrentUserInfo getCurrentUser(){
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();

        String sql = "select "+CurrentUserTable.COL_NAME + " from "+CurrentUserTable.TABLE_NAME;
        Cursor cursor = readableDatabase.rawQuery(sql, null);
        if(cursor.moveToNext()){
            currentUserInfo = new CurrentUserInfo();

            currentUserInfo.setCurrentUserName(cursor.getString(cursor.getColumnIndex(CurrentUserTable.COL_NAME)));
        }
        cursor.close();

        return currentUserInfo;
    }

    public void addCurrentUser(String username){
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();
        String sql = "insert into "+CurrentUserTable.TABLE_NAME +" values ("+username+")";
        readableDatabase.execSQL(sql);
    }

    public void deleteCurrentUser(){
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();
        String sql = "delete from "+CurrentUserTable.TABLE_NAME;
        readableDatabase.execSQL(sql);
    }


}
