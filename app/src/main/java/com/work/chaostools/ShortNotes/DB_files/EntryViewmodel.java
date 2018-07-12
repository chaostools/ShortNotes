package com.work.chaostools.ShortNotes.DBfiles;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by ansh on 22/12/17.
 */

public class EntryViewmodel extends AndroidViewModel {
    private DBQueryHandler queryHandler;

    public EntryViewmodel(@NonNull Application application) {
        super(application);
        queryHandler=new DBQueryHandler(application);
    }


    public void insertAtend(DBEntryStructure... entry) {
        queryHandler.insertAtend(entry);

    }
    public void removeEntry(DBEntryStructure... entry) {
        queryHandler.remove(entry);

    }
    public void update(DBEntryStructure... entry) {
        queryHandler.update(entry);

    }

    public void removeAll() {
        queryHandler.removeAll();
    }

    public LiveData<List<DBEntryStructure>> getAllentries() {
        return queryHandler.getAllentries();
    }


    public DBEntryStructure getEntrybyPositon(int pos){
        return  queryHandler.getEntrybyPositon(pos);
    }


}
