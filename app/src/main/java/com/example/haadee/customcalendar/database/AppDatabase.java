package com.example.haadee.customcalendar.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Haadee on 3/9/2018.
 */
@Database(entities = {Event.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "CustomCalendarDb")
                    .build();
            return INSTANCE;
        }else {
            return INSTANCE;
        }
    }

    public static void destroyDatabaseInstance(){
        INSTANCE = null;
    }
}
