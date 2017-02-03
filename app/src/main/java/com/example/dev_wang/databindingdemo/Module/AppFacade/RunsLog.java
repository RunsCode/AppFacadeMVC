package com.example.dev_wang.databindingdemo.Module.AppFacade;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dev_wang on 2017/1/25.
 */

public abstract class RunsLog {
    /**
     * * 打印日志时获取当前的程序文件名、行号、方法名 输出格式为：[FileName | LineNumber | MethodName]
     * *
     * * @return
     * */
    public static String TAG() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[2];
        StringBuffer toStringBuffer = new StringBuffer("[").append(
                traceElement.getFileName()).append(" | ").append(
                traceElement.getLineNumber()).append(" | ").append(
                traceElement.getMethodName()).append("]");
        return toStringBuffer.toString();
    }

    //当前文件名
    public static String _FILE_() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getFileName();
    }

    // 当前方法名
    public static String _FUNC_() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getMethodName();
    }

    // 当前行号
    public static int _LINE_() {
        StackTraceElement traceElement = ((new Exception()).getStackTrace())[1];
        return traceElement.getLineNumber();
    }

    // 当前时间
    public static String _TIME_() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(now);
    }

    public static void v(String log) {
        Log.v(RunsLog.TAG(),log);
    }

    public static void d(String log) {
        Log.d(RunsLog.TAG(),log);
    }

    public static void i(String log) {
        Log.i(RunsLog.TAG(),log);
    }

    public static void w(String log) {
        Log.w(RunsLog.TAG(),log);
    }

    public static void e(String log) {
        Log.e(RunsLog.TAG(),log);
    }


}
