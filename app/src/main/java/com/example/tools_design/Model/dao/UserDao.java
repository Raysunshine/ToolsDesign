package com.example.tools_design.Model.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.tools_design.Model.bean.UserInfo;
import com.example.tools_design.Model.db.UserDB;
import com.example.tools_design.Utils.Constant;

public class UserDao {

    private final UserDB mHelper;
    private UserInfo userInfo;

    public UserDao(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        mHelper = new UserDB(context, name, factory, version);
    }

    /**
     * 添加用户信息
     * @param userInfo
     */
    public void addUser(UserInfo userInfo){
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(UserTable.COL_NAME,userInfo.getUserName());
        contentValues.put(UserTable.COL_NICKNAME,userInfo.getNickName());
        contentValues.put(UserTable.COL_PASSWORD,userInfo.getPassword());
        contentValues.put(UserTable.COL_IS_ONLINE,userInfo.getIsOnline());

        readableDatabase.replace(UserTable.TABLE_NAME,null,contentValues);
    }

    /**
     * 判断是否存在该用户
     * @param userName
     * @return
     */
    @SuppressLint("Range")
    public boolean isUsernameExisted(String userName){
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();
        String sql = "select "+UserTable.COL_NAME +" from "+UserTable.TABLE_NAME + " where "+UserTable.COL_NAME +" =?";

        Cursor cursor = readableDatabase.rawQuery(sql, new String[]{userName});
        String username = "";
        if (cursor.moveToNext()){
            username = cursor.getString(cursor.getColumnIndex(UserTable.COL_NAME));
        }

        return TextUtils.isEmpty(username)?false:true;

    }

    /**
     * 登录成功，修改在线状态
     * @param userName
     */
    public void loginIn(String userName){
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();

        String sql = "update "+UserTable.TABLE_NAME + " set "+UserTable.COL_IS_ONLINE +" =? "+" where "+UserTable.COL_NAME + " =? ";
        readableDatabase.execSQL(sql,new String[]{"1",userName});
    }

    /**
     * 退出登录，修改在线状态
     * @param userName
     */
    public void logOut(String userName){
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();
        String sql = "update "+UserTable.TABLE_NAME +" set "+UserTable.COL_IS_ONLINE +" =? "+" where "+UserTable.COL_NAME + " =? ";
        readableDatabase.execSQL(sql,new String[]{"1",userName});
    }

    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    @SuppressLint("Range")
    public UserInfo getUserInformationByUserName(String userName){
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();

        String sql = "select * from "+UserTable.TABLE_NAME+" where "+UserTable.COL_NAME+" =? ";
        Cursor cursor = readableDatabase.rawQuery(sql, new String[]{userName});

        if (cursor.moveToNext()){
            userInfo = new UserInfo();

            userInfo.setUserName(cursor.getString(cursor.getColumnIndex(UserTable.COL_NAME)));
            userInfo.setNickName(cursor.getString(cursor.getColumnIndex(UserTable.COL_NICKNAME)));
            userInfo.setPassword(cursor.getString(cursor.getColumnIndex(UserTable.COL_PASSWORD)));
            userInfo.setIsOnline(cursor.getInt(cursor.getColumnIndex(UserTable.COL_IS_ONLINE)));
        }

        cursor.close();

        return userInfo;
    }

    /**
     * 获取在线用户名
     * @return
     */
    @SuppressLint("Range")
    public String getCurrentUser(){
        SQLiteDatabase readableDatabase = mHelper.getReadableDatabase();

        String sql = "select "+UserTable.COL_NAME +" from "+UserTable.TABLE_NAME +" where "+UserTable.COL_IS_ONLINE + "= 1";

        Cursor cursor = readableDatabase.rawQuery(sql, null);
        String userName = "";
        if (cursor.moveToNext()){
            userName = cursor.getString(cursor.getColumnIndex(UserTable.COL_NAME));
        }

        return userName;
    }
}
