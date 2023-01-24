package com.abir.roomlivedata.DATABASE;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CART_MODEL.class}, version = 1, exportSchema = false)
public abstract class ROOM_LIVEDATA_DATABASE extends RoomDatabase {
    public abstract CART_DAO cartDao();

    private static ROOM_LIVEDATA_DATABASE INSTANCE;

    static ROOM_LIVEDATA_DATABASE getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ROOM_LIVEDATA_DATABASE.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ROOM_LIVEDATA_DATABASE.class, "cart_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
