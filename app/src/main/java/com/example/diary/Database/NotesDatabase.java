package com.example.diary.Database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {NotesEntry.class}, version = 2, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    private static final String LOG_TAG = NotesDatabase.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "todolist";
    private static NotesDatabase sInstance;

    public static NotesDatabase createdb(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d("newinstance", "Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        NotesDatabase.class, NotesDatabase.DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration()
                        .build();
            }
        }
        Log.d("instance", "Getting the database instance");
        return sInstance;
    }

    public abstract DaoClass Dao();

}
