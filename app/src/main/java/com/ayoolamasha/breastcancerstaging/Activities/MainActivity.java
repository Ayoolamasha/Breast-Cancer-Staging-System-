package com.ayoolamasha.breastcancerstaging.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoolamasha.breastcancerstaging.R;

public class MainActivity extends AppCompatActivity {
    private RadioGroup lumpStateRg, lumpSizeRg, lumpAttachmentRg, nodeStateRg, nodePositionRg, tumorStateRg;
    private RadioButton palpable, nonPalpable, sizeLessThan2, size2to5, size5more,
    attachedUnder, attachedOver, nodeSwollen, nodeNotSwollen,
            nodeSwollenMobile, nodeSwollenFixed, spreadPositive, spreadNegative;
    private Button stage, refresh, clickHere;
    private TextView stageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewsSetup();



        stage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((sizeLessThan2.isChecked() || nonPalpable.isChecked()) && nodeNotSwollen.isChecked()){
                    stageText.setVisibility(View.VISIBLE);
                    stageText.setText(getResources().getText(R.string.stage_1));
                    //stageText.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    buttonAnim();


                }
                else if(nonPalpable.isChecked() && nodeSwollen.isChecked()){
                    stageText.setVisibility(View.VISIBLE);
                    stageText.setText(getResources().getText(R.string.stage_2));
                    clickHere.setVisibility(View.VISIBLE);


                }
                else if (sizeLessThan2.isChecked() && nodeSwollen.isChecked()){
                    stageText.setVisibility(View.VISIBLE);
                    stageText.setText(getResources().getText(R.string.stage_2));
                    clickHere.setVisibility(View.VISIBLE);

                }
                else if (size2to5.isChecked() && nodeNotSwollen.isChecked()){
                    stageText.setVisibility(View.VISIBLE);
                    stageText.setText(getResources().getText(R.string.stage_2));
                    clickHere.setVisibility(View.VISIBLE);

                }
                else if (size5more.isChecked() && nodeSwollenMobile.isChecked()){
                    stageText.setVisibility(View.VISIBLE);
                    stageText.setText(getResources().getText(R.string.stage_2));
                    clickHere.setVisibility(View.VISIBLE);

                }
                else if (nonPalpable.isChecked() && nodeSwollenFixed.isChecked()){
                    stageText.setVisibility(View.VISIBLE);
                    stageText.setText(getResources().getText(R.string.stage_3));
                    clickHere.setVisibility(View.VISIBLE);

                }
                else if ((sizeLessThan2.isChecked() || size2to5.isChecked() || size5more.isChecked())
                        &&(attachedUnder.isChecked() || attachedOver.isChecked())){
                    stageText.setVisibility(View.VISIBLE);
                    stageText.setText(getResources().getText(R.string.stage_3));
                    clickHere.setVisibility(View.VISIBLE);

                }
                else if (sizeLessThan2.isChecked() || (size2to5.isChecked())
                        || (size5more.isChecked()) && nodeSwollenFixed.isChecked()){

                    stageText.setVisibility(View.VISIBLE);
                    stageText.setText(getResources().getText(R.string.stage_3));
                    clickHere.setVisibility(View.VISIBLE);

                }
                else if ((palpable.isChecked() || nonPalpable.isChecked()) || (attachedUnder.isChecked()
                        || attachedOver.isChecked()) || (nodeSwollen.isChecked() || nodeNotSwollen.isChecked()) && spreadPositive.isChecked()){
                    stageText.setVisibility(View.VISIBLE);
                    stageText.setText(getResources().getText(R.string.stage_4));
                    clickHere.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(MainActivity.this, "Option not valid", Toast.LENGTH_SHORT).show();
                }

            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                lumpStateRg.clearCheck();
//                lumpSizeRg.clearCheck();
//                lumpAttachmentRg.clearCheck();
//                nodeStateRg.clearCheck();
//                nodePositionRg.clearCheck();
//                tumorStateRg.clearCheck();
//                stageText.setVisibility(View.GONE);
//                clickHere.setVisibility(View.GONE);

                Intent intent = new Intent(getApplicationContext(), PatientRecordActivity.class);
                startActivity(intent);

            }
        });
    }

    private void viewsSetup(){
        palpable = findViewById(R.id.palpableLump);
        nonPalpable = findViewById(R.id.non_palpableLump);
        sizeLessThan2 = findViewById(R.id.size2cm);
        size2to5 = findViewById(R.id.size2cmTo5cm);
        size5more = findViewById(R.id.size5cmPlus);
        attachedUnder = findViewById(R.id.underMuscle);
        attachedOver = findViewById(R.id.overMuscle);
        nodeSwollen = findViewById(R.id.nodeSwollen);
        nodeNotSwollen = findViewById(R.id.nodeNotSwollen);
        nodeSwollenMobile = findViewById(R.id.nodeSwollenMobile);
        nodeSwollenFixed = findViewById(R.id.nodeSwollenFixed);
        spreadPositive = findViewById(R.id.spreadPositive);
        spreadNegative = findViewById(R.id.spreadNegative);
        stage = findViewById(R.id.stageBtn);
        refresh = findViewById(R.id.refreshBtn);
        lumpStateRg = findViewById(R.id.lumpStateRg);
        lumpSizeRg = findViewById(R.id.lumpPalpableSizeRg);
        lumpAttachmentRg = findViewById(R.id.lumpPalpableAttachmentRg);
        nodeStateRg = findViewById(R.id.nodeStateRg);
        nodePositionRg = findViewById(R.id.nodeSwollenPositionRg);
        tumorStateRg = findViewById(R.id.tumorSpreadRg);
        stageText = findViewById(R.id.stageText);
        clickHere = findViewById(R.id.clickHere);

        stageText.setVisibility(View.GONE);
        clickHere.setVisibility(View.GONE);
    }

    private void buttonAnim(){
        clickHere.setVisibility(View.VISIBLE);
        Animation sideAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_anim);
        clickHere.setAnimation(sideAnimation);
    }


    public void refresh(View view) {
        lumpStateRg.clearCheck();
        lumpSizeRg.clearCheck();
        lumpAttachmentRg.clearCheck();
        nodeStateRg.clearCheck();
        nodePositionRg.clearCheck();
        tumorStateRg.clearCheck();
        stageText.setVisibility(View.GONE);
        clickHere.setVisibility(View.GONE);
    }
}