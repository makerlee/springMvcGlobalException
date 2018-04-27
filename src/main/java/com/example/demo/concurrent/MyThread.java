package com.example.demo.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lijiyang on 2017/10/12.
 */
public class MyThread extends Thread{
    private Date createDate;
    private Date startDate;
    private Date finishDate;

    public MyThread(Runnable target, String name) {
        super(target, name);
        this.createDate = new Date();
    }

    @Override
    public void run() {
        setStartTime();
        super.run();
        setFinishTime();
    }

    public void setStartTime() {
        startDate = new Date();
    }
    public void setFinishTime(){
        finishDate = new Date();
    }

    public long getExecutionTime() {
        return finishDate.getTime()-startDate.getTime();
    }

    @Override
    public String toString(){
        StringBuilder buffer=new StringBuilder();
        buffer.append(getName());
        buffer.append(": ");
        buffer.append(" Creation Date: ");
        buffer.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createDate));
        buffer.append(" : Running time: ");
        buffer.append(getExecutionTime());
        buffer.append(" Milliseconds.");
        return buffer.toString();
    }


}
