package com.ayoolamasha.breastcancerstaging.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.ayoolamasha.breastcancerstaging.Database.Records;
import com.ayoolamasha.breastcancerstaging.R;


public class PatientRecordDetailsActivity extends AppCompatActivity {

    // STATIC FIELDS
    public static final String EXTRA_PATIENT_ID =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_ID";
    public static final String EXTRA_PATIENT_NAME =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_NAME";
    public static final String EXTRA_PATIENT_AGE =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_AGE";
    public static final String EXTRA_PATIENT_SYMPTOMS =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_SYMPTOMS";
    public static final String EXTRA_PATIENT_STAGE =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_STAGE";


    private TextView patientName, patientAge, patientSymptoms, patientStage;
    private ImageView backArrow, editDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_details);
        viewsSetup();
        goBack();
        editPatientDetails();

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_PATIENT_ID)){
            patientName.setText(intent.getStringExtra(EXTRA_PATIENT_NAME));
            patientAge.setText(intent.getStringExtra(EXTRA_PATIENT_AGE));
            patientSymptoms.setText(intent.getStringExtra(EXTRA_PATIENT_SYMPTOMS));
            patientStage.setText(intent.getStringExtra(EXTRA_PATIENT_STAGE));
        }else {
            Toast.makeText(this, "Record Not Available", Toast.LENGTH_SHORT).show();
        }



    }

    private void viewsSetup(){
        patientName = findViewById(R.id.patientName);
        patientAge = findViewById(R.id.agePatient);
        patientSymptoms = findViewById(R.id.patientSymptoms);
        patientStage = findViewById(R.id.patientStage);
        backArrow = findViewById(R.id.detailsBack);
        editDetails = findViewById(R.id.editDetails);
    }

    private void editPatientDetails(){
        editDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Records records = new Records();
                Intent intent = new Intent(PatientRecordDetailsActivity.this, AddNewRecordActivity.class);
                intent.putExtra(AddNewRecordActivity.EXTRA_PATIENT_ID, records.getId());
                intent.putExtra(AddNewRecordActivity.EXTRA_PATIENT_NAME, records.getPatientName());
                intent.putExtra(AddNewRecordActivity.EXTRA_PATIENT_AGE, String.valueOf(records.getPatientAge()) );
                intent.putExtra(AddNewRecordActivity.EXTRA_PATIENT_SYMPTOMS, records.getStagingDetails());
                intent.putExtra(AddNewRecordActivity.EXTRA_PATIENT_STAGE, String.valueOf(records.getStageFigure()) );

                startActivity(intent);


            }
        });

    }

    private void goBack(){
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
