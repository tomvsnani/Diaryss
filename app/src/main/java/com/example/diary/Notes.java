package com.example.diary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.diary.Database.NotesDatabase;
import com.example.diary.Database.NotesEntry;
import com.example.diary.databinding.ActivityNotesBinding;

public class Notes extends AppCompatActivity {
    public static final String new_notes = "new_notes";
    public static final String default_notes = "old_notes";
    static int id;
    static NotesEntry s, s1;
    static String notes_assigner;
    public NotesDatabase database = NotesDatabase.createdb(this);
    ActivityNotesBinding binding;
    Intent intent;
    int counter = 1;
    Context context;
    NotesEntry notesEntry;
    String new_text;
    long i;
    int j = 0;
    String textt;

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return super.getLifecycle();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        //  binding.deletetext.setOnClickListener();
        //binding.forwardtext.setOnClickListener();
        //binding.previoustext.setOnClickListener();
        //binding.savetext.setOnClickListener();
        intent = getIntent();
        notes_assigner = intent.getStringExtra("newnotes");
        id = intent.getIntExtra("id", -1);
        final String notes_text = intent.getStringExtra("text");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes);
        Log.d("bookand", notes_assigner);
        // Log.d("notestext",notes_text);
       getLifecycle().addObserver(new LifeCycleOfThisClass());
        LiveData<String> text = database.Dao().getnewstring(i);
        text.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textt = s;
            }
        });
//

        try {
            if (notes_assigner.equals(default_notes)) {
                binding.editText2.setText(notes_text);
             //   binding.previoustext.setText(String.valueOf(id));
                binding.editText2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        String old_text = binding.editText2.getText().toString();
                        notesEntry = new NotesEntry();
                        notesEntry.setText(old_text);
                        notesEntry.setId(id);
                        database.Dao().update(notesEntry);
                    }
                });
            } else if (notes_assigner.equals(new_notes)) {
                binding.editText2.addTextChangedListener(new TextWatcher() {
                    @Override
                    public int hashCode() {
                        return super.hashCode();
                    }

                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        new_text = binding.editText2.getText().toString();
                        notesEntry = new NotesEntry();
                        notesEntry.setText(new_text);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();



        if (notes_assigner.equals(new_notes) && new_text != null && !new_text.isEmpty() && !new_text.equals(textt)) {
            if (j == 0) {
                Executor_assigner.instance().exec().execute(new Runnable() {
                    @Override
                    public void run() {
                        i = database.Dao().insertall(notesEntry);
                        j = 1;
                        Log.d("j=0", "0");
                        Log.d("insertnotes", new_text);

                    }
                });
            } else {
                Executor_assigner.instance().exec().execute(new Runnable() {
                    @Override
                    public void run() {
                        notesEntry.setId((int) i);
                        database.Dao().update(notesEntry);

                        Log.d("j=1", "0");
                        Log.d("pausenotes", new_text);
                        j = 0;
                    }
                });


            }
        }
    }

    class LifeCycleOfThisClass implements LifecycleObserver {
       // public LifeCycleOfThisClass(Lifecycle lifecycle){}
    //    @OnLifecycleEvent((Lifecycle.Event.ON_CREATE))
        void mai()
        {}
    }
}
