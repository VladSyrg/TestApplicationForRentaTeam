package com.syrgdev.testapplicationforrentateam.persistance.room;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import static com.syrgdev.testapplicationforrentateam.BuildConfig.DB_NAME;

@androidx.room.Database(
        entities = {UserEntity.class},
        version = 1,
        exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract UserEntity.DAO userEntityDao();

    private static volatile Database INSTANCE = null;

    public synchronized static Database getRoomDatabase(Context context) {
        return (getRoomDatabase(context, false));
    }

    private synchronized static Database getRoomDatabase(@SuppressWarnings("SpellCheckingInspection") Context context,
                                                         @SuppressWarnings("SameParameterValue") boolean memoryOnly) {
        if (INSTANCE == null) {
            INSTANCE = create(context, memoryOnly);
        }
        return INSTANCE;
    }

    private static Database create(Context context, boolean memoryOnly) {
        Builder<Database> builder;
        if (memoryOnly) {
            builder = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                    Database.class);
        } else {
            builder = Room.databaseBuilder(context.getApplicationContext(), Database.class, DB_NAME);
        }
        return builder.build();
    }

}
