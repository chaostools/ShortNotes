package com.work.chaostools.ShortNotes;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.work.chaostools.ShortNotes.DBfiles.DBEntryStructure;
import com.work.chaostools.ShortNotes.DBfiles.EntryViewmodel;
import com.work.chaostools.ShortNotes.Rvfiles.RvAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Main Activity";
    TextView intro;
    RecyclerView rv;
    FloatingActionButton fabnew;
    List<DBEntryStructure> feeder;
    RvAdapter adp;
    Toolbar toolbar;

    private EntryViewmodel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbarmain);
        setSupportActionBar(toolbar);
        //for feeder =getdataArr();
        //fixme: add an empty arr first,start a thread ,read db and update the adapter's feeder later
        intro = findViewById(R.id.introtext);


        model = ViewModelProviders.of(this).get(EntryViewmodel.class);


        Log.e(TAG, "onCreate:on create called called,creating layouts on screent ");
        rv = (RecyclerView) findViewById(R.id.mainRVlayoutID);
        feeder = new ArrayList<>();
        adp = new RvAdapter(feeder,model);
        rv.setAdapter(adp);
        GridLayoutManager layout=new GridLayoutManager(this,3);
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            layout.setSpanCount(5);
        }
        rv.setLayoutManager(layout);

        Log.e(TAG, "onCreate: setted up layouts now calling the database:");

        model.getAllentries().observe(this, new Observer<List<DBEntryStructure>>() {
            @Override
            public void onChanged(@Nullable List<DBEntryStructure> dbEntryStructures) {
                adp.setRvDataArr(dbEntryStructures);
                if (dbEntryStructures.isEmpty()||dbEntryStructures==null) {
                    intro.setVisibility(View.VISIBLE);
                } else {
                    intro.setVisibility(View.INVISIBLE);

                }
            }
        });


        fabnew = (FloatingActionButton) findViewById(R.id.fablayoutid);
        fabnew.setOnClickListener(new View.OnClickListener() {
            public static final String TAG = "main activity";

            @Override
            public void onClick(View v) {

                Intent openNewNote = new Intent(v.getContext(), NoteActivity.class);


                startActivity(openNewNote);


            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        inflates systems' built in menu with our menu
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.menu_main_about) {
            startActivity(new Intent(MainActivity.this, AboutActivity.class));

        }
        if (i == R.id.menu_main_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));

        }
        if (i == R.id.menu_main_about) {

        }


        return super.onOptionsItemSelected(item);
    }


    //    public void UpdateDataArr() {
//
//
//        final ArrayList<RvData> dataArr = adp.getRvDataArrayList();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.e(TAG, "<<UpdateDataArr>>:  calling databasein a new thread");
//
//                AppDB dbobject = Room.databaseBuilder(MainActivity.this, AppDB.class, "AllNotes.db")
//                        .build();
//
//
//                List<DBEntryStructure> allnotes = dbobject.allActions().getAllnotes();
//                Log.e(TAG, "UpdateDataArr: allnotes recieved are:");
//                for (DBEntryStructure i : allnotes) {
//                    dataArr.add(new RvData(i.getTitle(), i.getData()));
//                    Log.e(TAG, "recievedNote: " + i);
//                }
//
//
//            }
//        }).start();
//        adp.setRvDataArrayList(dataArr);
//        adp.notifyDataSetChanged();
//
//
//    }
}


