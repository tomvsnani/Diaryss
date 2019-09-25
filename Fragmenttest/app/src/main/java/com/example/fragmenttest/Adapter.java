package com.example.fragmenttest;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter{
    int size;
    Button t1;
    public Adapter(int size)
    {
        this.size=size;
    }
class Viewholder extends RecyclerView.ViewHolder{

    public Viewholder(@NonNull View itemView) {
        super(itemView);
         t1=(Button) itemView.findViewById(R.id.button);
        Log.d("reached4","e");
    }
}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_view,parent,false);
        Viewholder v1=new Viewholder(v);
        Log.d("reached2","e");
        return v1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        t1.setText(String.valueOf(position));
        Log.d("reached3","e");
    }

    @Override
    public int getItemCount() {
        return size;
    }
}
