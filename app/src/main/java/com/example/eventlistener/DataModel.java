package com.example.eventlistener;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class DataModel {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "action")
    public String action;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "time")
    public String time;

    public DataModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
