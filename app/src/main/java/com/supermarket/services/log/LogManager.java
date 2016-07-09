package com.supermarket.services.log;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by think on 2016/6/16.
 */
public class LogManager {
    private Queue<String> logList = new LinkedList<String>();
    private volatile static LogManager instance;

    private LogManager() {

    }

    public static LogManager getInstance() {
        if (instance == null) {
            synchronized (LogManager.class) {
                if (instance == null) {
                    instance = new LogManager();
                }
            }
        }

        return instance;
    }

    public void addLog(String log) {
        logList.offer(log);
    }

    public Queue<String> getLogList() {
        return logList;
    }
}
