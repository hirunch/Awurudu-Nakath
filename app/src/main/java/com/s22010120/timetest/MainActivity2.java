package com.s22010120.timetest;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {

    private ImageButton soundToggleButton;
    private TextView n2Countdown1;
    private TextView n2Countdown2;
    private TextView n2Countdown3;
    private TextView n2Countdown4;
    private TextView n2Countdown5;
    private TextView n2Countdown6;
    private CountDownTimer countDownTimer1;
    private CountDownTimer countDownTimer2;
    private CountDownTimer countDownTimer3;
    private CountDownTimer countDownTimer4;
    private CountDownTimer countDownTimer5;
    private CountDownTimer countDownTimer6;


    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        AudioController.startIfNeeded(this);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView adView = (AdView)findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        adView.loadAd(adRequest);

        AdView adView2 = (AdView)findViewById(R.id.adView3);
        AdRequest adrequest2 = new AdRequest.Builder()
                .build();
        adView2.loadAd(adrequest2);

        soundToggleButton = findViewById(R.id.soundToggleBtn);
        updateSoundIcon();
        soundToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean nextMutedState = !AudioController.isMuted(MainActivity2.this);
                AudioController.setMuted(MainActivity2.this, nextMutedState);
                updateSoundIcon();
            }
        });


        n2Countdown1 = findViewById(R.id.textn1);
        n2Countdown2 = findViewById(R.id.textn2);
        n2Countdown3 = findViewById(R.id.textn3);
        n2Countdown4 = findViewById(R.id.textn4);
        n2Countdown5 = findViewById(R.id.textn5);
        n2Countdown6 = findViewById(R.id.textn6);

        try {
            Date futureDateTime1 = dateFormat.parse(NakathSchedule.NAKATH_ONE);
            long futureTimeInMillis1 = futureDateTime1.getTime();
            Date futureDateTime2 = dateFormat.parse(NakathSchedule.NAKATH_TWO);
            long futureTimeInMillis2 = futureDateTime2.getTime();
            Date futureDateTime3 = dateFormat.parse(NakathSchedule.NAKATH_THREE);
            long futureTimeInMillis3 = futureDateTime3.getTime();
            Date futureDateTime4 = dateFormat.parse(NakathSchedule.NAKATH_FOUR);
            long futureTimeInMillis4 = futureDateTime4.getTime();
            Date futureDateTime5 = dateFormat.parse(NakathSchedule.NAKATH_FIVE);
            long futureTimeInMillis5 = futureDateTime5.getTime();
            Date futureDateTime6 = dateFormat.parse(NakathSchedule.NAKATH_SIX);
            long futureTimeInMillis6 = futureDateTime6.getTime();

            long currentTimeInMillis = System.currentTimeMillis();
            long timeDiff1 = futureTimeInMillis1 - currentTimeInMillis;
            long timeDiff2 = futureTimeInMillis2 - currentTimeInMillis;
            long timeDiff3 = futureTimeInMillis3 - currentTimeInMillis;
            long timeDiff4 = futureTimeInMillis4 - currentTimeInMillis;
            long timeDiff5 = futureTimeInMillis5 - currentTimeInMillis;
            long timeDiff6 = futureTimeInMillis6 - currentTimeInMillis;

            startCountdown1(timeDiff1);
            startCountdown2(timeDiff2);
            startCountdown3(timeDiff3);
            startCountdown4(timeDiff4);
            startCountdown5(timeDiff5);
            startCountdown6(timeDiff6);
//            scheduleNotification(timeDiff1, 1);
//            scheduleNotification(timeDiff2, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        AudioController.startIfNeeded(this);
        updateSoundIcon();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isFinishing() && isTaskRoot()) {
            AudioController.release();
        }
    }

    private void updateSoundIcon() {
        if (soundToggleButton == null) {
            return;
        }
        boolean muted = AudioController.isMuted(this);
        soundToggleButton.setImageResource(
                muted ? android.R.drawable.ic_lock_silent_mode : android.R.drawable.ic_lock_silent_mode_off
        );
    }

    private void startCountdown2(long millisInFuture) {
        countDownTimer1 = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countdownText = getCountdownText(millisUntilFinished);
                n2Countdown1.setText(countdownText);
            }

            @Override
            public void onFinish() {
                n2Countdown1.setText("සුභ අලුත් අවුරුද්දක් වේවා!");


            }
        }.start();
    }

    private void startCountdown1(long millisInFuture) {
        countDownTimer2 = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countdownText = getCountdownText(millisUntilFinished);
                n2Countdown2.setText(countdownText);
            }

            @Override
            public void onFinish() {
                n2Countdown2.setText("");
            }
        }.start();
    }

    private void startCountdown3(long millisInFuture){

        countDownTimer3 = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countdownText = getCountdownText(millisUntilFinished);
                n2Countdown3.setText(countdownText);
            }

            @Override
            public void onFinish() {
                n2Countdown3.setText("");

            }
        }.start();
    }


    private void startCountdown4(long millisInFuture) {
        countDownTimer4 = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countdownText = getCountdownText(millisUntilFinished);
                n2Countdown4.setText(countdownText);
            }

            @Override
            public void onFinish() {
                n2Countdown4.setText("");
            }
        }.start();
    }


    private void startCountdown5(long millisInFuture) {
        countDownTimer5 = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countdownText = getCountdownText(millisUntilFinished);
                n2Countdown5.setText(countdownText);
            }

            @Override
            public void onFinish() {
                n2Countdown5.setText("");
            }
        }.start();
    }


    private void startCountdown6(long millisInFuture) {
        countDownTimer6 = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String countdownText = getCountdownText(millisUntilFinished);
                n2Countdown6.setText(countdownText);
            }

            @Override
            public void onFinish() {
                n2Countdown6.setText("");
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
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer1 != null) {
            countDownTimer1.cancel();
        }
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        if (countDownTimer3 != null) {
            countDownTimer3.cancel();
        }
        if (countDownTimer4 != null) {
            countDownTimer4.cancel();
        }
        if (countDownTimer5 != null) {
            countDownTimer5.cancel();
        }
        if (countDownTimer6 != null) {
            countDownTimer6.cancel();
        }
    }


}