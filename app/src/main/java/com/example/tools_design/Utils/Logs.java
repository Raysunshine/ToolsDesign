package com.example.tools_design.Utils;

import android.util.Log;

public class Logs {

    private static final String TAG = "Raysun";
    private static final int capacity = 91;
    private static final StringBuffer defaultLine = new StringBuffer(capacity);
    private static final StringBuffer frontLine = new StringBuffer(45);
    private static final StringBuffer behindLine = new StringBuffer(45);
    private static final StringBuffer frontBlank = new StringBuffer(45);
    private static final StringBuffer behindBlank = new StringBuffer(45);

    /**
     * Error
     *
     * @param msg 错误信息
     */
    public static void e(String msg) {
        setLines(msg.length());
        Log.e(TAG, String.valueOf(defaultLine));
        Log.e(TAG, frontLine + "" + frontBlank + msg + behindBlank + "" + behindLine);
        Log.e(TAG, String.valueOf(defaultLine));
        clearStringBuffer();
    }

    /**
     * Debug
     *
     * @param msg 调试信息
     */
    public static void d(String msg) {
        setLines(msg.length());
        Log.d(TAG, String.valueOf(defaultLine));
        Log.d(TAG, frontLine + "" + frontBlank + msg + behindBlank + "" + behindLine);
        Log.d(TAG, String.valueOf(defaultLine));
        clearStringBuffer();
    }

    /**
     * Info
     *
     * @param msg 提示信息
     */
    public static void i(String msg) {
        setLines(msg.length());
        Log.i(TAG, String.valueOf(defaultLine));
        Log.i(TAG, frontLine + "" + frontBlank + msg + behindBlank + "" + behindLine);
        Log.i(TAG, String.valueOf(defaultLine));
        clearStringBuffer();
    }

    /**
     * Verbose
     *
     * @param msg 冗长信息
     */
    public static void v(String msg) {
        setLines(msg.length());
        Log.v(TAG, String.valueOf(defaultLine));
        Log.v(TAG, frontLine + "" + frontBlank + msg + behindBlank + "" + behindLine);
        Log.v(TAG, String.valueOf(defaultLine));
        clearStringBuffer();
    }

    /**
     * Warning
     *
     * @param msg 警告信息
     */
    public static void w(String msg) {
        setLines(msg.length());

        Log.w(TAG, String.valueOf(defaultLine));
        Log.w(TAG, frontLine + "" + frontBlank + msg + behindBlank + "" + behindLine);
        Log.w(TAG, String.valueOf(defaultLine));

        clearStringBuffer();
    }

    /**
     * 设置线条
     *
     * @param msgLength 信息的长度
     */
    private static void setLines(int msgLength) {

        int sideLineLength;

        if (msgLength % 2 == 0) {
            int evenCapacity = capacity - 1;
            for (int i = 0; i < evenCapacity; i++) {
                defaultLine.append('~');
            }
            if (msgLength > evenCapacity) {
                return;
            }
            sideLineLength = (evenCapacity - msgLength) / 2;
        } else {
            for (int i = 0; i < capacity; i++) {
                defaultLine.append('~');
            }
            if (msgLength > capacity) {
                return;
            }
            sideLineLength = (capacity - msgLength) / 2;
        }
        for (int i = 0; i < sideLineLength / 2; i++) {
            frontLine.append('~');
            behindLine.append('~');
            frontBlank.append(' ');
            behindBlank.append(' ');
        }
        if (sideLineLength % 2 != 0) {
            frontLine.append('~');
            behindLine.append('~');
        }
    }

    /**
     * 清除所有stringBuffer
     */
    private static void clearStringBuffer() {
        defaultLine.delete(0, defaultLine.length());
        frontLine.delete(0, frontLine.length());
        behindLine.delete(0, behindLine.length());
        frontBlank.delete(0, frontBlank.length());
        behindBlank.delete(0, behindBlank.length());
    }

}
