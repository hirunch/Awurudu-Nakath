package com.s22010120.timetest;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FrstPageActivity extends AppCompatActivity {

    private TextView allNakathBtn;
    private FrameLayout compassBtn, frameOne, frameTwo, frameThree, frameFour, frameFive, frameSix;

    private TextView timeCountdownOne, timeCountdownTwo, timeCountdownThree, timeCountdownFour, timeCountdownFive, timeCountdownSix;
    private CountDownTimer countDownTimerOne, countDownTimerTwo, countDownTimerThree, countDownTimerFour, countDownTimerFive, countDownTimerSix;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    private String firstBoxnakathOne = "2025-04-13 20:57:00";
    private String secondBoxnakathtwo = "2025-04-14 03:21:00";
    private String thirdBoxnakathThree = "2025-04-14 04:04:00";
    private String fourthBoxnakathFour = "2025-04-14 06:44:00";
    private String fifthBoxnakathfive = "2025-04-16 09:04:00";
    private String sixthBoxnakathsix = "2025-04-17 09:03:00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frst_page);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = (AdView)findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        adView.loadAd(adRequest);


        allNakathBtn = findViewById(R.id.nakathView);
        compassBtn = findViewById(R.id.compassBtn);

        timeCountdownOne = findViewById(R.id.nakathOne);
        timeCountdownTwo = findViewById(R.id.nakathTwo);
        timeCountdownThree = findViewById(R.id.nakathThree);
        timeCountdownFour = findViewById(R.id.nakathFour);
        timeCountdownFive = findViewById(R.id.nakathFive);
        timeCountdownSix = findViewById(R.id.nakathSix);

        frameOne = findViewById(R.id.framenOne);
        frameTwo = findViewById(R.id.framenTwo);
        frameThree = findViewById(R.id.framenThree);
        frameFour = findViewById(R.id.framenFour);
        frameFive = findViewById(R.id.framenFive);
        frameSix = findViewById(R.id.framenSix);

        try {
            Date futureNakathDateOne = dateFormat.parse(firstBoxnakathOne);
            long futureNakathMilllisOne = futureNakathDateOne.getTime();
            Date futureNakathDateTwo = dateFormat.parse(secondBoxnakathtwo);
            long futureNakathMilllisTwo = futureNakathDateTwo.getTime();
            Date futureNakathDateThree = dateFormat.parse(thirdBoxnakathThree);
            long futureNakathMilllisThree = futureNakathDateThree.getTime();
            Date futureNakathDateFour = dateFormat.parse(fourthBoxnakathFour);
            long futureNakathMilllisFour = futureNakathDateFour.getTime();
            Date futureNakathDateFive = dateFormat.parse(fifthBoxnakathfive);
            long futureNakathMilllisFive = futureNakathDateFive.getTime();
            Date futureNakathDateSix = dateFormat.parse(sixthBoxnakathsix);
            long futureNakathMilllisSix = futureNakathDateSix.getTime();


            long currentTimeMills = System.currentTimeMillis();

            long timeDiffOne = futureNakathMilllisOne - currentTimeMills;
            long timeDiffTwo = futureNakathMilllisTwo - currentTimeMills;
            long timeDiffThree = futureNakathMilllisThree - currentTimeMills;
            long timeDiffFour = futureNakathMilllisFour - currentTimeMills;
            long timeDiffFive = futureNakathMilllisFive - currentTimeMills;
            long timeDiffSix = futureNakathMilllisSix - currentTimeMills;


            startCountdownOne(timeDiffOne);
            startCountdownTwo(timeDiffTwo);
            startCountdownThree(timeDiffThree);
            startCountdownFour(timeDiffFour);
            startCountdownFive(timeDiffFive);
            startCountdownSix(timeDiffSix);


        } catch (Exception e){
            e.printStackTrace();
        }



        allNakathBtn.setOnClickListener(new View.OnClickListener() {
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

        ImageView gifsun = findViewById(R.id.rotatedSun);
        ImageView gifcompass = findViewById(R.id.compass);

        Glide.with(this)
                .asGif()
                .load(R.drawable.rotatedsun)
                .into(gifsun);

        Glide.with(this)
                .asGif()
                .load(R.drawable.compassgif)
                .into(gifcompass);

    }

    private void startCountdownOne(long millsFuture){
        countDownTimerOne = new CountDownTimer(millsFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countdownText = getCountdownText(millisUntilFinished);
                timeCountdownOne.setText(countdownText);
            }

            @Override
            public void onFinish() {
                frameOne.setVisibility(View.GONE);
            }
        }.start();
    }
    private void startCountdownTwo(long millsFuture){
        countDownTimerTwo = new CountDownTimer(millsFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countdownText = getCountdownText(millisUntilFinished);
                timeCountdownTwo.setText(countdownText);
            }

            @Override
            public void onFinish() {
                frameTwo.setVisibility(View.GONE);
            }
        }.start();
    }
    private void startCountdownThree(long millsFuture){
        countDownTimerThree = new CountDownTimer(millsFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countdownText = getCountdownText(millisUntilFinished);
                timeCountdownThree.setText(countdownText);
            }

            @Override
            public void onFinish() {
                frameThree.setVisibility(View.GONE);
            }
        }.start();
    }
    private void startCountdownFour(long millsFuture){
        countDownTimerFour = new CountDownTimer(millsFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countdownText = getCountdownText(millisUntilFinished);
                timeCountdownFour.setText(countdownText);
            }

            @Override
            public void onFinish() {
                frameFour.setVisibility(View.GONE);
            }
        }.start();
    }

    private void startCountdownFive(long millsFuture){
        countDownTimerFive = new CountDownTimer(millsFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countdownText = getCountdownText(millisUntilFinished);
                timeCountdownFive.setText(countdownText);
            }

            @Override
            public void onFinish() {
                frameFive.setVisibility(View.GONE);
            }
        }.start();
    }

    private void startCountdownSix(long millsFuture){
        countDownTimerSix = new CountDownTimer(millsFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countdownText = getCountdownText(millisUntilFinished);
                timeCountdownSix.setText(countdownText);
            }

            @Override
            public void onFinish() {
                frameSix.setVisibility(View.GONE);
            }
        }.start();
    }

    private String getCountdownText(long millisUntilFinished) {
        long days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished);
        long hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished) % 24;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60;

        if (days == 0 && hours == 0 && minutes == 0) {
            return String.format(Locale.getDefault(), "තත්පර %02d", seconds);
        } else if (days == 0 && hours == 0) {
            return String.format(Locale.getDefault(), "මිනිත්තු %02d: තත්පර %02d", minutes, seconds);
        } else if (days == 0) {
            return String.format(Locale.getDefault(), "පැය %02d: මිනිත්තු %02d: තත්පර %02d", hours, minutes, seconds);
        } else {
            return String.format(Locale.getDefault(), "දින: %02d \nපැය %02d: මිනිත්තු %02d: තත්පර %02d",
                    days, hours, minutes, seconds);
        }
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(countDownTimerOne != null) {
            countDownTimerOne.cancel();
        }
        if(countDownTimerTwo != null) {
            countDownTimerTwo.cancel();
        }
        if(countDownTimerThree != null) {
            countDownTimerThree.cancel();
        }
        if(countDownTimerFour != null) {
            countDownTimerFour.cancel();
        }
        if(countDownTimerFive != null) {
            countDownTimerFive.cancel();
        }
        if(countDownTimerSix != null) {
            countDownTimerSix.cancel();
        }

    }


}