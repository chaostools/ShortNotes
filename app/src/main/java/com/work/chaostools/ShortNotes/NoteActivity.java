package com.work.chaostools.ShortNotes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.work.chaostools.ShortNotes.DBfiles.DBEntryStructure;
import com.work.chaostools.ShortNotes.DBfiles.EntryViewmodel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class NoteActivity extends AppCompatActivity {
    private static final String TAG = "NoteActivity...";
    EditText titleET;
    EditText noteET;
    TextView dateView;
    TextView timeView;
    DBEntryStructure recievedNote;
    FloatingActionButton fab_savenote;

    EntryViewmodel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar t = findViewById(R.id.toolbarNote);
        setSupportActionBar(t);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(false);
        ab.setElevation(0);



        model = ViewModelProviders.of(this).get(EntryViewmodel.class);

        titleET = (EditText) findViewById(R.id.titleIDactivity2);
        noteET = (EditText) findViewById(R.id.editnotelayoutIDactivity2);
        dateView=findViewById(R.id.text_date_l);
        timeView=findViewById(R.id.text_time_l);

        fab_savenote=findViewById(R.id.fab_save_note);
        fab_savenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savedata();
                finish();
            }
        });


        Intent recievedIntent = getIntent();

        recievedNote = recievedIntent.getParcelableExtra("NOTE");
        if(recievedNote!=null){
            Log.e(TAG, "onCreate: recieved entrystructure is not null.old note is recieved" );
            titleET.setText(recievedNote.getTitle());
            noteET.setText(recievedNote.getData());
            dateView.setText(recievedNote.getDate());
            timeView.setText(recievedNote.getTime());
        }
        else{
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df;

            df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(c.getTime());


            df = new SimpleDateFormat("h:mm a");
            String formattedtime = df.format(c.getTime());


            dateView.setText(formattedDate);
            timeView.setText(formattedtime);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        inflates systems' built in menu with our menu
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuitem_DeleteNote) {
            deleteNote();
            finish();
        } else if (item.getItemId() == R.id.menuitem_saveNote) {
            savedata();
            finish();
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        savedata();
        super.onBackPressed();
    }

    private void savedata() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df;

        df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());


        df = new SimpleDateFormat("h:mm a");
        String formattedtime = df.format(c.getTime());
        if (recievedNote != null) {
            //oldnote recieved
            recievedNote.setTitle(titleET.getText().toString());
            recievedNote.setData(noteET.getText().toString());


            recievedNote.setDate(formattedDate);
            recievedNote.setTime(formattedtime);


            model.update(recievedNote);
            Log.e(TAG, "savedata: note updated" );
        } else {
            Log.e(TAG, "savedata: ");

            DBEntryStructure entry =
                    new DBEntryStructure(titleET.getText().toString(), noteET.getText().toString()
                            ,formattedDate,formattedtime);
            model.insertAtend(entry);
            Log.e(TAG, "savedata:new  note inserted" );

        }
        Toast.makeText(this, "saved!", Toast.LENGTH_SHORT).show();

        model.getAllentries().observe(NoteActivity.this, new Observer<List<DBEntryStructure>>() {
            @Override
            public void onChanged(@Nullable List<DBEntryStructure> dbEntryStructures) {
                Log.e(TAG, "onSaveddata: " + dbEntryStructures);
            }
        });
    }

    private void deleteNote() {
        if(recievedNote!=null){
            model.removeEntry(recievedNote);
        }
    }

}
