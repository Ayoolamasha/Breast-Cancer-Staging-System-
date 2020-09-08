package com.ayoolamasha.breastcancerstaging.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordsViewModel extends AndroidViewModel {
    private RecordsRepository recordsRepository;
    private LiveData<List<Records>> allRecords;
    public RecordsViewModel(@NonNull Application application) {
        super(application);
        recordsRepository = new RecordsRepository(application);
        allRecords = recordsRepository.getAllRecords();
    }

    public void insert(Records records){
        recordsRepository.insert(records);
    }

    public void update(Records records){
        recordsRepository.update(records);
    }
    public void delete(Records records){
        recordsRepository.delete(records);
    }
    public void deleteAll(){
        recordsRepository.deleteAllRecords();
    }

    public LiveData<List<Records>> getAllRecords(){
        return allRecords;
    }
}
