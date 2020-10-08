package com.ayoolamasha.breastcancerstaging.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.ayoolamasha.breastcancerstaging.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class AddNewRecordActivity extends AppCompatActivity {
     // STATIC FIELDS
    public static final String EXTRA_PATIENT_ID =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_ID";
    public static final String EXTRA_PATIENT_NAME =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_NAME";
    public static final String EXTRA_PATIENT_AGE =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_AGE";
    public static final String EXTRA_PATIENT_SYMPTOMS =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_SYMPTOMS";
    public static final String EXTRA_PATIENT_STAGE =  "com.ayoolamasha.breastcancerstaging.Activities.EXTRA_PATIENT_STAGE";

    private TextInputEditText patientName, patientAge,patientSymptoms;
    private TextView patientInfo;
    private NumberPicker stagePicker;
    private ImageView backArrow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_add_new_patient_record);
        viewSetUp();
        goBack();


        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_PATIENT_ID)){
            patientInfo.setText(getResources().getString(R.string.toolbar_details_2));

            patientName.setText(intent.getStringExtra(EXTRA_PATIENT_NAME));
            patientAge.setText(intent.getStringExtra(EXTRA_PATIENT_AGE));
            patientSymptoms.setText(intent.getStringExtra(EXTRA_PATIENT_SYMPTOMS));
            stagePicker.setValue(intent.getIntExtra(EXTRA_PATIENT_STAGE, -1));
        }else {
            patientInfo.setText(getResources().getString(R.string.add_info));
        }
    }

    private void viewSetUp(){
        patientName = findViewById(R.id.editTextPatientName);
        patientAge = findViewById(R.id.editTextPatientAge);
        patientSymptoms = findViewById(R.id.editTextPatientSymptoms);
        stagePicker = findViewById(R.id.StagePicker);
        stagePicker.setMinValue(1);
        stagePicker.setMaxValue(4);
        patientInfo = findViewById(R.id.toolbarAddPatientInfo);
        backArrow = findViewById(R.id.backEdit);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void save(View view) {
        saveRecords();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void saveRecords(){
        String getPatientName = Objects.requireNonNull(patientName.getText()).toString().trim();
        //String getPatientAge = Objects.requireNonNull(patientAge.getText()).toString().trim();
        int getPatientAge = Integer.parseInt(Objects.requireNonNull(patientAge.getText()).toString().trim());
        String getPatientSymptoms = Objects.requireNonNull(patientSymptoms.getText()).toString().trim();
        int getPatientStageNumber =  stagePicker.getValue();

        if (getPatientName.isEmpty() || getPatientSymptoms.isEmpty()){
            Toast.makeText(this, "Please Fill All Required Fields", Toast.LENGTH_SHORT).show();
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_PATIENT_NAME, getPatientName);
        data.putExtra(EXTRA_PATIENT_AGE, getPatientAge);
        data.putExtra(EXTRA_PATIENT_SYMPTOMS, getPatientSymptoms);
        data.putExtra(EXTRA_PATIENT_STAGE, getPatientStageNumber);

        int id = getIntent().getIntExtra(EXTRA_PATIENT_ID, -1);
        if (id != -1){
            data.putExtra(EXTRA_PATIENT_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
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
