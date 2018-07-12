package com.work.chaostools.ShortNotes.Rvfiles;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.work.chaostools.ShortNotes.R;

/**
 * Created by ansh on 16/10/17.
 */

public class RvHolder extends RecyclerView.ViewHolder{
    public TextView mainrvnotetitle;
    TextView dateTime;

    public RvHolder(View itemView) {
        super(itemView);
        mainrvnotetitle=itemView.findViewById(R.id.mainrveachitemDefualttextview);
        dateTime=itemView.findViewById(R.id.rv_eachrow_date_time_text);



    }
}
