package com.example.diary.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface DaoClass {


    //@Query("SELECT * FROM Notes")
   // List<String> allNotesNames();

    @Query("SELECT * FROM Notes")
    LiveData<List<NotesEntry>> loadalltasks();

    @Query("DELETE FROM Notes")
    void deleteNotes();

  //  @Query("SELECT id FROM Notes WHERE text =:text")
  //  String getidontext(String text);

   // @Query("INSERT INTO  NOTES (text,id )VALUES (:Text ,:id)")
          // void insertonid(int id,String Text);

    @Insert
    Long insertall(NotesEntry notesEntries);

  //  @Insert
   // void inserttext(String text);

@Query("SELECT text FROM notes WHERE id=:id")
LiveData<String> getnewstring(long id);


    @Delete
    int delete(NotesEntry notesEntries);

   @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(NotesEntry notesEntries);

}
