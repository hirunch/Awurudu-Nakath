package com.s22010120.timetest;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FrstPageActivity extends AppCompatActivity {

    private FrameLayout frameLayout1;
    private FrameLayout frameLayout2;
    private FrameLayout compassBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frst_page);

        frameLayout1 = findViewById(R.id.nakathList1);
        frameLayout2 = findViewById(R.id.nakathList2);
        compassBtn = findViewById(R.id.compassBtn);




        frameLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FrstPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        frameLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FrstPageActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        compassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FrstPageActivity.this, compassActivity.class);
                startActivity(intent);
            }
        });


    }


}