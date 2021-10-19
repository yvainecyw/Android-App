package com.example.Room1;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.sql.Time;

@Entity(tableName = "Event_table")
public class Event {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "DateTime")
    private String DateTime;

    @ColumnInfo(name = "Task")
    private String Task;

    @ColumnInfo(name = "Date")
    private String Date;

    @ColumnInfo(name = "Time")
    private String Time;

    @ColumnInfo(name = "isRemind")
    private boolean Remind;

    @ColumnInfo(name = "Type")
    private String Type;

    @ColumnInfo(name = "Limit_hour")
    private int Limit_hour;

    @ColumnInfo(name = "Limit_minue")
    private int Limit_minue;

    @ColumnInfo(name = "isFinish")
    private boolean isFinish;

    @ColumnInfo(name = "Remind_friend")
    private boolean Remind_friend;

    public Event(Event event){
        this.Task = event.getTask();
        this.Date = event.getDate();
        this.Time = event.getTime();
        this.Remind = event.isRemind();
        this.Type = event.getType();
        this.Limit_hour = event.getLimit_hour();
        this.Limit_minue = event.getLimit_minue();
        this.DateTime = event.getDateTime();
        this.isFinish = false;
        this.Remind_friend = false;
    }

    public Event(@NonNull String Task, @NonNull String Date, @NonNull String Time,@NonNull boolean Remind,@NonNull String Type,int Limit_hour,int Limit_minue) {
        this.Task = Task;
        this.Date = Date;
        this.Time = Time;
        this.Remind = Remind;
        this.Type = Type;
        this.Limit_hour = Limit_hour;
        this.Limit_minue = Limit_minue;
        this.DateTime = Date + " " + Time;
        this.isFinish =false;
        this.Remind_friend = false;
    }
    @NonNull
    public String getDateTime() {return DateTime;}

    public void setDateTime(@NonNull String dateTime) { DateTime = dateTime;}

    public String getTask() {return Task; }

    public void setTask(String task) { Task = task; }

    public String getDate() {return Date; }

    public void setDate(String date) {Date = date; }

    public String getTime() {return Time; }

    public void setTime(String time) {Time = time; }

    public boolean isRemind() {return Remind; }

    public void setRemind(boolean remind) { Remind = remind; }

    public String getType() {return Type;}

    public void setType(String type) {Type = type; }


    public int getLimit_hour() {return Limit_hour; }

    public void setLimit_hour(int limit_hour) { Limit_hour = limit_hour; }

    public int getLimit_minue() { return Limit_minue; }

    public void setLimit_minue(int limit_minue) { Limit_minue = limit_minue; }

    public boolean isFinish() {return isFinish;}

    public void setFinish(boolean finish) {isFinish = finish;}

    public boolean isRemind_friend() {return Remind_friend; }

    public void setRemind_friend(boolean remind_friend) { Remind_friend = remind_friend; }
}
