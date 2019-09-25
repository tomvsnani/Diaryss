package com.example.diary;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diary.Database.NotesDatabase;
import com.example.diary.Database.NotesEntry;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter {
    private Button button2;
    private NotesDatabase notesDatabase;
    private NotesEntry notes_swipe;
    private RecyclerView.ViewHolder v1;
    private int numofitems;
    private Viewholder1 viewholder1;
    private int adapterposition;
    private List<NotesEntry> notesEntryList = new ArrayList<>();
    private int id;
    private String text;
    private ExtraMethod extraMethod;
    private TextView textview1;
    private Itemlistener itemlistener;
    ItemTouchHelper.SimpleCallback simpleCallback= new ItemTouchHelper.SimpleCallback(1, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return true;

        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            temp_delete_notes(viewHolder);

        }
    };



    public RecyclerAdapter(int numofitems, Itemlistener itemlistener, NotesDatabase notesDatabase, ExtraMethod extraMethod) {

        this.numofitems = numofitems;
        this.itemlistener = itemlistener;
        this.notesDatabase = notesDatabase;
        this.extraMethod = extraMethod;
        // this.setHasStableIds(true);
    }


    public void temp_delete_notes(RecyclerView.ViewHolder viewHolder) {
        notes_swipe = notesEntryList.remove(viewHolder.getAdapterPosition());
        extraMethod.recyclerView.getLayoutManager().removeAllViews();
        notifyDataSetChanged();
        snackbar(viewHolder.getAdapterPosition(), notes_swipe);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview1, parent, false);
        v1 = new Viewholder1(v);
        return v1;
    }

    public void setdata(List<NotesEntry> notesEntries) {
        try {
            notesEntryList = notesEntries;
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public List<NotesEntry> getnotesentries() {
        return notesEntryList;

    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        try {
            adapterposition = position;

            NotesEntry notesEntries = notesEntryList.get(position);
            id = notesEntries.getId();
            text = notesEntries.getText();
            button2.setText(String.valueOf(id));
            textview1.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (!notesEntryList.isEmpty()) {
            return notesEntryList.size();
        } else
        return 0;
    }

    public interface Itemlistener {
        void clicked(int i, View v, int d, String s);
    }

    class Viewholder1 extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Viewholder1(@NonNull View itemView) {
            super(itemView);
            try {
                textview1 =  itemView.findViewById(R.id.textView2);
                button2 =  itemView.findViewById(R.id.button2);
                textview1.setOnClickListener(this);
                button2.setOnClickListener(this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Override
        public void onClick(View view) {
            itemlistener.clicked(getAdapterPosition(), view, notesEntryList.get(getAdapterPosition()).getId(), notesEntryList.get(getAdapterPosition()).getText());

        }
    }
    public void snackbar(final int position, final NotesEntry notes_swipe) {

        View view_snackbar = ExtraMethod.view.findViewById(R.id.snackbar);
        Snackbar snackbar = Snackbar.make(view_snackbar, "Notes Seleted", Snackbar.LENGTH_LONG);
        snackbar.setAction("UNDO", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("hhhhh", "hh");
                notesEntryList.add(position, notes_swipe);
                notifyDataSetChanged();
            }
        });
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                if (event == Snackbar.Callback.DISMISS_EVENT_TIMEOUT) {
                    notesDatabase.Dao().delete(notes_swipe);
                }
                extraMethod.recyclerView.getLayoutManager().removeAllViews();
            }
        });
        snackbar.show();
    }

}






