package com.work.chaostools.ShortNotes.Rvfiles;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.work.chaostools.ShortNotes.DBfiles.DBEntryStructure;
import com.work.chaostools.ShortNotes.DBfiles.EntryViewmodel;
import com.work.chaostools.ShortNotes.NoteActivity;
import com.work.chaostools.ShortNotes.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static android.app.PendingIntent.getActivity;

/**
 * Created by ansh on 16/10/17.
 */


public class RvAdapter extends RecyclerView.Adapter<RvHolder> {
    private List<DBEntryStructure> rvDataArr;
    EntryViewmodel model;

    public RvAdapter(List<DBEntryStructure> rvDataArr, EntryViewmodel model) {
        this.rvDataArr = rvDataArr;
        this.model = model;
    }

    //constructor

    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return inflatedView(parent, viewType);


    }

    private RvHolder inflatedView(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rv_eachitemlayout,
                parent, false);

        return new RvHolder(v);
    }

    @Override
    public void onBindViewHolder(final RvHolder holder, final int position) {
        final TextView titlenote = holder.mainrvnotetitle;

        String title=rvDataArr.get(position).getTitle();
        if(title.equals("")){
            titlenote.setText("{no title}");
        }
        else {
            titlenote.setText(title);
        }
        titlenote.setBackground(getBgDrawable(holder,position));

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df;

        df = new SimpleDateFormat("h:mm a , dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        holder.dateTime.setText(formattedDate);


        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //runintent openOldNote
                Intent openOldNote = new Intent(v.getContext(), NoteActivity.class);
                openOldNote.putExtra("NOTE", rvDataArr.get(position));
                v.getContext().startActivity(openOldNote);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                android.support.v7.app.AlertDialog al = new android.support.v7.app.AlertDialog.Builder(v.getContext())
                        .setTitle("Delete?")
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                model.removeEntry(rvDataArr.get(position));
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .create();
                al.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return rvDataArr.size();
    }

    public List<DBEntryStructure> getRvDataArr() {
        return rvDataArr;
    }

    public void setRvDataArr(List<DBEntryStructure> rvDataArr) {
        this.rvDataArr = rvDataArr;
        notifyDataSetChanged();
    }


    public Drawable getBgDrawable(RvHolder holder,int pos) {


        int color_no=(pos)%6;

        Resources resources=holder.itemView.getResources();
        switch (color_no){

            case 0:holder.dateTime.setTextColor(R.color.blue_note);return resources.getDrawable(R.drawable.notebg0);
            case 1:holder.dateTime.setText(R.color.green_note);return resources.getDrawable(R.drawable.notebg1);
            case 2:holder.dateTime.setTextColor(R.color.blue_light_note);return resources.getDrawable(R.drawable.notebg2);
            case 3:holder.dateTime.setTextColor(R.color.red_note);return resources.getDrawable(R.drawable.notebg3);
            case 4:holder.dateTime.setTextColor(R.color.yellow_note);return resources.getDrawable(R.drawable.notebg4);
            case 5:holder.dateTime.setTextColor(R.color.orange_note);return resources.getDrawable(R.drawable.notebg5);
            default:holder.dateTime.setTextColor(R.color.yellow_note);return resources.getDrawable(R.drawable.notebg4);

        }
    }
}


