package com.example.diary;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class RecyclerAdapter extends RecyclerView.Adapter {
    View v;
    int viewtype;
    int numofitems;
    public TextView textview1;
    public TextView textview2;
    public Button b1,b2;
Itemlistener itemlistener;





    public RecyclerAdapter(int numofitems, Itemlistener itemlistener){
        this.numofitems=numofitems;


        this.itemlistener = itemlistener;
    }

    @Override
    public int getItemViewType(int position) {
        viewtype=1;
        if(position==0)
            viewtype=0;
        Log.d("getItemViewCalled",String.valueOf(position));
        return viewtype;
    }

    class Viewholder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Viewholder1(@NonNull View itemView) {
            super(itemView);
            textview1 = (TextView) itemView.findViewById(R.id.textView2);
            b1 = (Button) itemView.findViewById(R.id.button2);
            b1.setOnClickListener(this);
            textview1.setOnClickListener(this);

            Log.d("ViewHolder1","h");
        }

        @Override
        public void onClick(View view) {

            itemlistener.clicked(getAdapterPosition(), view);
            Log.d("atttt","fine");

        }

    }
  class Viewholder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Viewholder2(@NonNull View itemView) {
            super(itemView);
            textview2 = (TextView) itemView.findViewById(R.id.textView3);
            b2 = (Button) itemView.findViewById(R.id.button3);
            Log.d("Viewholder2","k");
            b2.setOnClickListener(this);
            textview2.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            itemlistener.clicked(getAdapterPosition(), view);
            Log.d("at","fine");
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch(viewType) {

            case 0:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview1, parent, false);
                Viewholder1 v1=new Viewholder1(v);
                Log.d("Viewholder1 returned","k");
                return v1;



            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview2, parent, false);
                Viewholder2 v2=new Viewholder2(v);
                Log.d("Viewholder2 returned",String.valueOf(viewType));
                return v2;



        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        viewtype= getItemViewType(position);
        Log.d("ph",String.valueOf(viewtype));
        if(viewtype==0)
        {
            textview1.setText("this is first");
        }
        else {
            textview2.setText("this is second");
        }





    }

    @Override
    public int getItemCount() {
        return numofitems;
    }

    public interface Itemlistener
    {
         void clicked(int clickedposition, View v);

    }

        }






