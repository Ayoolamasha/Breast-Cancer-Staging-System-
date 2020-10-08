package com.ayoolamasha.breastcancerstaging.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.ayoolamasha.breastcancerstaging.R;



public class PatientRecordDetailsActivity extends AppCompatActivity {

    // STATIC FIELDS
    public static final String EXTRA_PATIENT_ID =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_ID";
    public static final String EXTRA_PATIENT_NAME =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_NAME";
    public static final String EXTRA_PATIENT_AGE =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_AGE";
    public static final String EXTRA_PATIENT_SYMPTOMS =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_SYMPTOMS";
    public static final String EXTRA_PATIENT_STAGE =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_STAGE";
    private static final int EDIT_PATIENT_RECORD_REQUEST = 2;


    private TextView patientName, patientAge, patientSymptoms, patientStage;
    private ImageView backArrow, editDetails;
    private String patientId, patientNameEdit, patientSymptomsEdit;
    private String patientAgeEdit, patientStageEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_patient_details);
        viewsSetup();
        goBack();
        editPatientDetails();

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_PATIENT_ID)){
            patientId = EXTRA_PATIENT_ID;
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
                patientNameEdit = String.valueOf(patientName.getText());
                patientAgeEdit = String.valueOf(patientAge.getText());
                patientSymptomsEdit = String.valueOf(patientSymptoms.getText());
                patientStageEdit = String.valueOf(patientStage.getText());


                Intent intent = new Intent(PatientRecordDetailsActivity.this, AddNewRecordActivity.class);
                intent.putExtra(AddNewRecordActivity.EXTRA_PATIENT_ID, patientId);
                intent.putExtra(AddNewRecordActivity.EXTRA_PATIENT_NAME, patientNameEdit);
                intent.putExtra(AddNewRecordActivity.EXTRA_PATIENT_AGE, patientAgeEdit );
                intent.putExtra(AddNewRecordActivity.EXTRA_PATIENT_SYMPTOMS, patientSymptomsEdit);
                intent.putExtra(AddNewRecordActivity.EXTRA_PATIENT_STAGE, patientStageEdit);

                startActivityForResult(intent, EDIT_PATIENT_RECORD_REQUEST);


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

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
