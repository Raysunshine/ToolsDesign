package com.example.tools_design.Model;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.tools_design.Model.dao.UserDao;
import com.example.tools_design.Model.dao.UserTable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 全局数据模型层
 */
public class Model {

    private Context mContext;
    @SuppressLint("StaticFieldLeak")
    private static Model model = new Model();
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private UserDao userDao;

    /**
     * 在Application中初始化
     */
    public void init(Context mContext){
        this.mContext = mContext;

        //创建用户账号数据库的操作类对象
        userDao = new UserDao(mContext,UserTable.DB_NAME,null,1);

    }

    /**
     * 构造
     */
    public Model() {
    }

    /**
     * 创建单例
     *
     * @return model
     */
    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    //获取全局线程池
    public ExecutorService getGlobalThreadPool(){
        return executorService;
    }

    //用户信息操作类
    public UserDao getUserDao(){
        if(userDao == null){
            userDao = new UserDao(mContext,UserTable.DB_NAME,null,1);
        }
        return userDao;
    }


}
