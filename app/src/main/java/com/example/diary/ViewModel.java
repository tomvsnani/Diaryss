package com.example.diary;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.diary.Database.NotesDatabase;
import com.example.diary.Database.NotesEntry;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    public LiveData<List<NotesEntry>> getNotes() {
        return notes;
    }

    private LiveData<List<NotesEntry>> notes;
    private static final String  TAG=ViewModel.class.getSimpleName();

    public ViewModel(@NonNull Application application) {
        super(application);
        NotesDatabase db=NotesDatabase.createdb(getApplication());
        Log.d("viewmodel", "Actively retrieving the tasks from the DataBase");

        notes=db.Dao().loadalltasks();
    }
}
