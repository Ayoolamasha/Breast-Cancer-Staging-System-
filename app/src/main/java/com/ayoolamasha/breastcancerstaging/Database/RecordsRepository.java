package com.ayoolamasha.breastcancerstaging.Database;

import android.app.Application;
import android.icu.text.AlphabeticIndex;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordsRepository {
    private RecordsDao recordsDao;
    private LiveData<List<Records>> allRecords;

    public RecordsRepository(Application application){
        RecordsDatabase recordsDatabase = RecordsDatabase.getInstance(application);
        recordsDao = recordsDatabase.recordsDao();
        allRecords = recordsDao.getAllRecords();

    }

    public void insert(Records records){
        new insertRecordAsyncTask(recordsDao).execute(records);

    }

    public void update(Records records){
        new updateRecordAsyncTask(recordsDao).execute(records);

    }
     public void delete(Records records){
        new deleteRecordsAsyncTask(recordsDao).execute(records);

     }

     public void deleteAllRecords(){
        new deleteAllRecordsAsyncTask(recordsDao).execute();

     }

     public LiveData<List<Records>> getAllRecords(){
        return allRecords;
     }

     private static class insertRecordAsyncTask extends AsyncTask<Records, Void, Void>{

        private RecordsDao recordsDao;

        private insertRecordAsyncTask(RecordsDao recordsDao){
            this.recordsDao = recordsDao;
        }

         @Override
         protected Void doInBackground(Records... records) {
            recordsDao.insert(records[0]);
             return null;
         }
     }

     private static class updateRecordAsyncTask extends AsyncTask<Records, Void, Void>{
        private RecordsDao recordsDao;

        private updateRecordAsyncTask(RecordsDao recordsDao){
            this.recordsDao = recordsDao;
        }

         @Override
         protected Void doInBackground(Records... records) {
            recordsDao.update(records[0]);
             return null;
         }
     }

     private static class deleteRecordsAsyncTask extends AsyncTask<Records, Void, Void>{
        private RecordsDao recordsDao;

        private deleteRecordsAsyncTask(RecordsDao recordsDao){
            this.recordsDao = recordsDao;
        }

         @Override
         protected Void doInBackground(Records... records) {
            recordsDao.delete(records[0]);
             return null;
         }
     }

     private static class deleteAllRecordsAsyncTask extends AsyncTask<Void,Void,Void>{
        private RecordsDao recordsDao;

        private deleteAllRecordsAsyncTask(RecordsDao recordsDao){
            this.recordsDao = recordsDao;
        }

         @Override
         protected Void doInBackground(Void... voids) {
            recordsDao.deleteAll();
             return null;
         }
     }


}
