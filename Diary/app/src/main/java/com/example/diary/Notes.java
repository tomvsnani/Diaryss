package com.example.diary;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;

import com.example.diary.databinding.ActivityNotesBinding;

public class Notes extends AppCompatActivity {
    ActivityNotesBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        binding.deletetext.setOnClickListener();
        binding.forwardtext.setOnClickListener();
        binding.previoustext.setOnClickListener();
        binding.savetext.setOnClickListener();
        binding.editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
