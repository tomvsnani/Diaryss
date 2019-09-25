package com.example.diary;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity  {

    ExtraMethod extra;
   static  Parcelable layoutstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v =  findViewById(R.id.layout);
        ViewModel viewModel = ViewModelProviders.of((this)).get(ViewModel.class);
        extra = new ExtraMethod(this, v, this,viewModel);
        extra.init();

    }
    @Override
    public void onResume() {
        super.onResume();
        extra.retrievetasks();


        Log.d("inreturn", "return");
    }

    @Override
    protected void onPause() {
        super.onPause();
        layoutstate=extra.recyclerView.getLayoutManager().onSaveInstanceState();
    }
}




