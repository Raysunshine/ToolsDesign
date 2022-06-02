package com.example.tools_design.Model;

import android.content.Context;
import android.util.Log;

import androidx.transition.Visibility;

import com.example.tools_design.Utils.Constant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 全局数据模型层
 */
public class Model {

    private Context mContext;
    private static Model model = new Model();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 在Application中初始化
     * @param mContext
     */
    public void init(Context mContext){
        this.mContext = mContext;
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


}
