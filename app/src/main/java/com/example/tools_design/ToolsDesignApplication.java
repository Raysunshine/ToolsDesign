package com.example.tools_design;

import android.app.Application;
import com.example.tools_design.Model.Model;

/**
 * 程序的入口，需要在
 * AndroidManifest.xml中
 * application中
 * 添加android:name属性来指定程序的入口
 */
public class ToolsDesignApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化数据模型层
        Model.getInstance().init(this);
    }
}
