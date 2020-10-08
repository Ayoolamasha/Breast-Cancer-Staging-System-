package com.ayoolamasha.breastcancerstaging.Database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Records.class}, version = 1)
public abstract class RecordsDatabase extends RoomDatabase {

    private static final String dbName = "patients_records";

    private static RecordsDatabase instance;
    public abstract RecordsDao recordsDao();

    public static synchronized RecordsDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context, RecordsDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback callback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateAsyncTask(instance).execute();
        }
    };

    private static class populateAsyncTask extends AsyncTask<Void,Void,Void> {
        private RecordsDao recordsDao;

        private populateAsyncTask(RecordsDatabase db) {
            recordsDao = db.recordsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            recordsDao.insert(new Records("Dummy Text 1", 55,
                    "Swipe Left or Right To Delete", 1));
            recordsDao.insert(new Records("Dummy Text 2", 44,
                    "Click On That Delete Action At The Top To Delete All Entry", 4));
            recordsDao.insert(new Records("Dummy Text 3", 33,
                    "Click On The Button Below TO Add A New Record", 3));
            return null;
        }
    }
}
