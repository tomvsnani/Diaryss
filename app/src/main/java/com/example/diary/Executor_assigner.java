package com.example.diary;

import android.content.Context;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Executor_assigner {
    private Executor exec;

    private static Executor_assigner executor_instance;

    public Executor_assigner(Executor e){
        this.exec=e;
    }

    public static Executor_assigner instance(){
        if(executor_instance==null)
        {
            executor_instance=new Executor_assigner(Executors.newSingleThreadExecutor());
        }

return executor_instance;
    }
public Executor exec(){
        return exec;
}

}
