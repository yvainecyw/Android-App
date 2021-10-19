package com.example.Room1;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Event.class}, version = 2, exportSchema = false)
public abstract class EventRoomDatabase extends RoomDatabase {

    public abstract EventDao eventDao();

    private static EventRoomDatabase INSTANCE;

    //private static final String DBNAME = "Event";

    /*static final Migration MIGRATION_1_2 = new Migration(1,2){
        @Override
        public void migrate(SupportSQLiteDatabase database){
            //從我們開始沒有改變桌子，這裡別無他法。
        }
    };*/

    public static EventRoomDatabase getDatabase(final Context context){
        synchronized (EventRoomDatabase.class){
            if(INSTANCE==null){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),EventRoomDatabase.class,"Event_table")
                        // Wipes and rebuilds instead of migrating
                        // if no Migration object.
                        // Migration is not part of this practical.
                        .addCallback(sRoomDatabaseCallback)
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void> {
        private final EventDao mDao;

        PopulateDbAsync(EventRoomDatabase db) {
            mDao = db.eventDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            //mDao.deleteAll();
            mDao.getAllEvents();
            return null;
        }
    }

    public static void destoryInstance(){
        if(INSTANCE!=null){
            INSTANCE.close();
            INSTANCE = null;
        }
    }

}
