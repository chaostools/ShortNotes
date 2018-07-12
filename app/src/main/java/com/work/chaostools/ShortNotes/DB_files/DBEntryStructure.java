package com.work.chaostools.ShortNotes.DBfiles;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ansh on 19/12/17.
 */

@Entity(tableName = "AllnotesTable")
public class DBEntryStructure implements Parcelable {

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "position")
    private int pos;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "data")
    private String data;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;


    public DBEntryStructure() {
    }

    public DBEntryStructure(String title, String data, String date, String time) {
        this.title = title;
        this.data = data;
        this.date = date;
        this.time = time;
    }

    protected DBEntryStructure(Parcel in) {
        pos = in.readInt();
        title = in.readString();
        data = in.readString();
        date = in.readString();
        time = in.readString();
    }

    public static final Creator<DBEntryStructure> CREATOR = new Creator<DBEntryStructure>() {
        @Override
        public DBEntryStructure createFromParcel(Parcel in) {
            return new DBEntryStructure(in);
        }

        @Override
        public DBEntryStructure[] newArray(int size) {
            return new DBEntryStructure[size];
        }
    };

    public int getPos() {
        return pos;
    }

    public String getTitle() {
        return title;
    }

    public String getData() {
        return data;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(pos);
        dest.writeString(title);
        dest.writeString(data);
        dest.writeString(date);
        dest.writeString(time);
    }

    @Override
    public String toString() {
        return "DBEntryStructure{" +
                "pos=" + pos +
                ", title='" + title + '\'' +
                ", data='" + data + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
