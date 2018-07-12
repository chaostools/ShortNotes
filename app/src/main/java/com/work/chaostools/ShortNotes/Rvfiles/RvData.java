package com.work.chaostools.ShortNotes.Rvfiles;

/**
 * Created by ansh on 16/10/17.
 */

public class RvData {
    private String title;
    private String note;

    public RvData(String title, String note) {//<constructor
        this.title = title;
        this.note = note;
    }//constructor>

    public String getTitle() {
        return title;
    }
    public String getNote() {
        return note;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setNote(String note) {
        this.note = note;
    }
}