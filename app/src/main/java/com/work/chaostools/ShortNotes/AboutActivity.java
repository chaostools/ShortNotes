package com.work.chaostools.ShortNotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar t=findViewById(R.id.toolbarAbout);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
}
