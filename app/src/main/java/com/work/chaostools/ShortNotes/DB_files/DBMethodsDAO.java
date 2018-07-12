package com.work.chaostools.ShortNotes.DBfiles;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by ansh on 19/12/17.
 */

@Dao
public interface DBMethodsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOneOrMany(DBEntryStructure... e);


    @Query("DELETE FROM AllnotesTable")
    void removeAllNotes();

//    @Query("DELETE FROM AllnotesTable  WHERE position = :i")
//    void removeNote(int i);


    @Query("SELECT * FROM AllnotesTable")
    LiveData<List<DBEntryStructure>> getAllnotes();

    @Delete
    void removeOneOrMany(DBEntryStructure...entries);



    @Update()
    void updateNote(DBEntryStructure... entries);

    @Query("SELECT * FROM AllnotesTable WHERE position = :position")
    DBEntryStructure getNoteByPosition(int position);



//    @Query("SELECT * FROM AllnotesTable WHERE position = :i ")
//    DBEntryStructure getNote(int i);

}
