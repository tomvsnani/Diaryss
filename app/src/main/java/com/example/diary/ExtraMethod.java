package com.example.diary;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diary.Database.NotesDatabase;
import com.example.diary.Database.NotesEntry;

import java.util.List;

public class ExtraMethod implements RecyclerAdapter.Itemlistener {
    public RecyclerView recyclerView;
    int numofitems;
    List<NotesEntry> notes;
    NotesDatabase db;
    Context context;
    LifecycleOwner lifecycleOwner;
    static View view;
    RecyclerAdapter.Itemlistener itemlistener;
    Button button;
    ViewModel viewModel;
    List<NotesEntry> entries;
    Parcelable layoutstate;
    public RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    int clickedposition;

    public ExtraMethod(Context context, View view, LifecycleOwner lifecycleOwner, ViewModel viewmodel) {
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.view = view;
        this.viewModel = viewmodel;
        db = NotesDatabase.createdb(context);
    }


    public ExtraMethod() {
        super();
    }

    public void retrievetasks() {
        try {

            viewModel.getNotes().observe(lifecycleOwner, new Observer<List<NotesEntry>>() {
                @Override
                public void onChanged(List<NotesEntry> notesEntries) {
                    layoutManager.removeAllViews();
                    notes = notesEntries;
                    adapter.setdata(notes);
                    //    recyclerView.scrollToPosition(adapter.adapterposition);
                    recyclerView.getLayoutManager().onRestoreInstanceState(MainActivity.layoutstate);
                    Log.d("outreturn", "return");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void init() {
        recyclerView = view.findViewById(R.id.recyclerView);
        button = view.findViewById(R.id.button);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        adapter = new RecyclerAdapter(numofitems, this,db,this);
        recyclerView.setLayoutManager(layoutManager);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(adapter.simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Notes.class);
                intent.putExtra("newnotes", "new_notes");
                context.startActivity(intent);
            }
        });
    }


    @Override
    public void clicked(int clickedposition, View v, int iditem, String textitem) {
        this.clickedposition=clickedposition;
        try {
            switch (v.getId()) {
                case R.id.button2:

                    entries = adapter.getnotesentries();// entries of notes in my list
                    String s = entries.get(clickedposition).getText();
                    int idd = entries.get(clickedposition).getId();

                    int n = db.Dao().delete(entries.get(clickedposition));
                    recyclerView.getLayoutManager().onRestoreInstanceState(layoutstate);
                    adapter.notifyDataSetChanged();
                    layoutstate = recyclerView.getLayoutManager().onSaveInstanceState();


                    break;
                case R.id.textView2:
                    Log.d("hai", "hai");
                    Intent intent = new Intent(context, Notes.class);
                    intent.putExtra("newnotes", "old_notes");
                    intent.putExtra("id", iditem);
                    intent.putExtra("text", textitem);
                    context.startActivity(intent);

                default:
                    Log.d("default", "hai");
            }

        } catch (Exception E) {
            E.printStackTrace();
        }

    }

    }



