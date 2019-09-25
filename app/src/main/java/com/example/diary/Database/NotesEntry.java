package com.example.diary.Database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "Notes")
public class NotesEntry {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private  String text;
    @Ignore
    private Date  date;

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }






    }

