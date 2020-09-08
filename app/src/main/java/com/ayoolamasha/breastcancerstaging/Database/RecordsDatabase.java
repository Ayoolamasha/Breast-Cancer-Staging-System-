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
            recordsDao.insert(new Records("Patient Zero", 55,
                    "Stage Palpable, Less than 2", 1));
            recordsDao.insert(new Records("Patient X", 44,
                    "Stage Underlying, Positive", 4));
            recordsDao.insert(new Records("Patient Jane Doe", 33,
                    "Stage 3 Symptoms", 3));
            return null;
        }
    }
}
