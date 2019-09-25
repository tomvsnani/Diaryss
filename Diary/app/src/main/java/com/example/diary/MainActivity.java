 package com.example.diary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diary.databinding.ActivityMainBinding;

 public class MainActivity extends AppCompatActivity implements RecyclerAdapter.Itemlistener {

     ActivityMainBinding binding;
     //Button b1 = findViewById(R.id.button2);
     RecyclerAdapter adapter;
     RecyclerAdapter.Viewholder1 v6;
     View v1;







     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         adapter = new RecyclerAdapter(2,this);

         RecyclerView recyclerView = new RecyclerView(this);
         RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

         binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
         binding.textView.setText("hello");



         binding.recycleid.setLayoutManager(layoutManager);
         binding.recycleid.setAdapter(adapter);
         Log.d("adapeter", "has set");
        //  v1=(View) v6.itemView.findViewById(R.id.button3);



     }


     @Override
     public void clicked(int clickedposition,View v) {



         Log.d("item id ", String.valueOf(clickedposition));
             try {
                 switch (clickedposition) {

                     case 0:
                         switch (v.getId()) {

                             case R.id.button2:
                                 Intent intent = new Intent(MainActivity.this, Notes.class);
                                 startActivity(intent);
                                 Log.d("button1", String.valueOf(clickedposition));
                                 break;
                             case R.id.textView2:
                                 Log.d("text3", String.valueOf(clickedposition));
                                 break;
                         }
                         break;


                     case 1:
                         switch (v.getId()) {

                             case R.id.button3:
                                 // Intent intent = new Intent(MainActivity.this, Notes.class);
                                 //startActivity(intent);
                                 Log.d("button1", String.valueOf(clickedposition));
                                 break;
                             case R.id.textView3:
                                 Log.d("text1", String.valueOf(clickedposition));
                                 break;
                         }
break;
                 }
                 }

             catch (Exception E)
             {
                 E.printStackTrace();
             }

         }
     }



