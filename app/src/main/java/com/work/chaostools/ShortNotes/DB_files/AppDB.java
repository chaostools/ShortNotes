package com.work.chaostools.ShortNotes.DBfiles;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by ansh on 19/12/17.
 */

@Database(entities = DBEntryStructure.class , version = 1)
public abstract class AppDB extends RoomDatabase {
    //abstract mean this class will not have a constructor but this class can be extended to other classes
    // @entity: a class that gives info about the structure of data to be stored: the variables and their datatypes
    //          however its name is the same as table name(^^^)
    //@dao(data access objects): an *interface* that gives the info about the methods that can be used with the database
    //this database class extending RoomDatabase:it acts as a linker to table and database.
    //          it makes an abstract object of tabledao interface, thus linking database to tablefunctions which
    //          are itself linking to allnotes.table(SELECT * from Allnotes,etc),therby linking table to db.
    //          now this class is needed to be used by other classesto actually erform some task
    //thus a table named AppDB with entries and data inthe form of 'DBentrystructure' will be made with version =1

    public abstract DBMethodsDAO allActions();


//    private static RoomDatabase.Callback onCallback=new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//        }
//
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//        }
//    };



    //Made the AppDB a singleton to prevent having multiple instances of the database opened
    // at the same time
    private static AppDB INSTANCE;
    public static AppDB getDatabaseInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDB.class, "AllNotes.db")
                    //i guess tablename shoud be same as name defined in @entity(i.e entryStructure Class)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
//synchronisd(class) is a function somewhat like a thread. if A and B tries to interact with'foo.class'
// at the same time,then B will wait until Call of A is complete.if they interact with a synchonised(foo.class) function.
//https://www.youtube.com/watch?v=RH7G-N2pa8M&t=546s

/*
SINGLETON PATTERN:In software engineering, the singleton pattern is a software design pattern that restricts
the instantiation of a class to one object. This is useful when exactly one object is needed to
coordinate actions across the system. The concept is sometimes generalized to systems that operate
more efficiently when only one object exists, or that restrict the instantiation to a certain number
of objects. The term comes from the mathematical concept of a singleton.
*/




/*  MISC:NAMING CONVENTIONS
* - data.java : works as a class 1)defining structure of data 2)defining column names 3) defining
*               table names . :. classname=DBEntryStructure.java BUT entity(tablename= "AllNotes"+"table"
*               i.e,
*                @Entity(tableName = "AllnotesTable")
*                public class DBEntryStructure {
*                    ...
*                    ...
*                    }
* - DAO(functions waala interface) : works as an interface providing functions for the class. :.
*                @Dao public interface "AllNotes"+TableFunctions {...}
* - Database : AppDB because this shows puri app ke liye ek hi db banega BUT iska object "ALLnotes.db
*              ke name se banaege
*
* */
