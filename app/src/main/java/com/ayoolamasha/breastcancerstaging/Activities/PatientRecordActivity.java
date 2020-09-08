package com.ayoolamasha.breastcancerstaging.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ayoolamasha.breastcancerstaging.Adapter.RecordsAdapter;
import com.ayoolamasha.breastcancerstaging.Database.Records;
import com.ayoolamasha.breastcancerstaging.Database.RecordsViewModel;
import com.ayoolamasha.breastcancerstaging.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class PatientRecordActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "PatientRecordActivity";

    private static final int ADD_NEW_RECORD_REQUEST = 1;
    private static final int VIEW_RECORD_REQUEST = 2;
    private RecordsViewModel recordsViewModel;
    private RecyclerView patientRecycler;
    private FloatingActionButton actionButton;
    private ImageView deleteAll, backArrow;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_records);
        viewsSetUp();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddNewRecordActivity.class);
                startActivityForResult(intent, ADD_NEW_RECORD_REQUEST);
            }
        });

        patientRecycler.setHasFixedSize(true);
        patientRecycler.setLayoutManager(new LinearLayoutManager(this));

        final RecordsAdapter adapter = new RecordsAdapter();
        patientRecycler.setAdapter(adapter);

        recordsViewModel = new ViewModelProvider(this).get(RecordsViewModel.class);
        recordsViewModel.getAllRecords().observe(this, new Observer<List<Records>>() {
            @Override
            public void onChanged(List<Records> records) {
                adapter.submitList(records);

            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                recordsViewModel.delete(adapter.getNoteAt(viewHolder.getAdapterPosition()));

                //Toast.makeText(PatientRecordActivity.this, "Patient Record Deleted!", Toast.LENGTH_SHORT).show();
                Snackbar.make(coordinatorLayout, "Patient Record Deleted!", Snackbar.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(patientRecycler);

        adapter.setOnItemClickListener(new RecordsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Records records) {
                Intent intent = new Intent(PatientRecordActivity.this, PatientRecordDetailsActivity.class);
                intent.putExtra(PatientRecordDetailsActivity.EXTRA_PATIENT_ID, records.getId());
                intent.putExtra(PatientRecordDetailsActivity.EXTRA_PATIENT_NAME, records.getPatientName());
                intent.putExtra(PatientRecordDetailsActivity.EXTRA_PATIENT_AGE, String.valueOf(records.getPatientAge()) );
                intent.putExtra(PatientRecordDetailsActivity.EXTRA_PATIENT_SYMPTOMS, records.getStagingDetails());
                intent.putExtra(PatientRecordDetailsActivity.EXTRA_PATIENT_STAGE, String.valueOf(records.getStageFigure()) );

                startActivity(intent);


            }

        });

        backArrow.setOnClickListener(this);
        deleteAll.setOnClickListener(this);


    }

    private void viewsSetUp(){
        actionButton = findViewById(R.id.addFab);
        patientRecycler = findViewById(R.id.patientRecycler);
        backArrow = findViewById(R.id.backArrowHome);
        deleteAll = findViewById(R.id.deleteAllPatient);
        coordinatorLayout = findViewById(R.id.coordinator);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NEW_RECORD_REQUEST && resultCode == RESULT_OK){
            assert data != null;
            String patientName = data.getStringExtra(AddNewRecordActivity.EXTRA_PATIENT_NAME);
            int patientAge = data.getIntExtra(AddNewRecordActivity.EXTRA_PATIENT_AGE, 1);
            Log.d(TAG, "onActivityResult: ");
            String patientSymptoms = data.getStringExtra(AddNewRecordActivity.EXTRA_PATIENT_SYMPTOMS);
            int patientStage = data.getIntExtra(AddNewRecordActivity.EXTRA_PATIENT_STAGE, 1);

            Records records = new Records(patientName, patientAge, patientSymptoms, patientStage);
            recordsViewModel.insert(records);

            //Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show();
            Snackbar.make(coordinatorLayout, "Record Saved", Snackbar.LENGTH_SHORT).show();
        }else if  (requestCode == VIEW_RECORD_REQUEST && resultCode == RESULT_OK){
            assert data != null;
            int id = data.getIntExtra(PatientRecordDetailsActivity.EXTRA_PATIENT_ID, -1);
            if (id == -1){
                Toast.makeText(this, "Record can't be viewed", Toast.LENGTH_SHORT).show();
                return;

            }
            String patientName = data.getStringExtra(AddNewRecordActivity.EXTRA_PATIENT_NAME);
            int patientAge = data.getIntExtra(AddNewRecordActivity.EXTRA_PATIENT_AGE, 1);
            String patientSymptoms = data.getStringExtra(AddNewRecordActivity.EXTRA_PATIENT_SYMPTOMS);
            int patientStage = data.getIntExtra(AddNewRecordActivity.EXTRA_PATIENT_STAGE, 1);

            Records records = new Records(patientName, patientAge, patientSymptoms, patientStage);
            // This helps the note to know what to update
            records.setId(id);
            recordsViewModel.update(records);

            Toast.makeText(this, "Records Updated!", Toast.LENGTH_SHORT).show();

        }else{
            Snackbar.make(coordinatorLayout, "Record Not Saved", Snackbar.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.backArrowHome:
                finish();
                break;

            case R.id.deleteAllPatient:
                recordsViewModel.deleteAll();
                //Toast.makeText(this, "Deleted All Patients Records", Toast.LENGTH_SHORT).show();
                Snackbar.make(coordinatorLayout, "Deleted All Patients Records", Snackbar.LENGTH_SHORT).show();
                break;

        }
    }
}
