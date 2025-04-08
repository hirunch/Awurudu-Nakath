package com.s22010120.timetest;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.FrameLayout;
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

    MediaPlayer mediaPlayer;

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


    private String nakathOne = "2025-04-14 03:21:00";
    private String nakathTwo = "2025-04-13 20:57:00";
    private String nakathThree = "2025-04-14 04:04:00";
    private String nakathFour = "2025-04-14 06:44:00";
    private String nakathFive = "2025-04-16 09:04:00";
    private String nakathSix = "2025-04-17 09:03:00";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

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

        mediaPlayer = MediaPlayer.create(this, R.raw.song2);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();


        n2Countdown1 = findViewById(R.id.textn1);
        n2Countdown2 = findViewById(R.id.textn2);
        n2Countdown3 = findViewById(R.id.textn3);
        n2Countdown4 = findViewById(R.id.textn4);
        n2Countdown5 = findViewById(R.id.textn5);
        n2Countdown6 = findViewById(R.id.textn6);

        try {
            Date futureDateTime1 = dateFormat.parse(nakathOne);
            long futureTimeInMillis1 = futureDateTime1.getTime();
            Date futureDateTime2 = dateFormat.parse(nakathTwo);
            long futureTimeInMillis2 = futureDateTime2.getTime();
            Date futureDateTime3 = dateFormat.parse(nakathThree);
            long futureTimeInMillis3 = futureDateTime3.getTime();
            Date futureDateTime4 = dateFormat.parse(nakathFour);
            long futureTimeInMillis4 = futureDateTime4.getTime();
            Date futureDateTime5 = dateFormat.parse(nakathFive);
            long futureTimeInMillis5 = futureDateTime5.getTime();
            Date futureDateTime6 = dateFormat.parse(nakathSix);
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

    private void startCountdown1(long millisInFuture) {
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

    private void startCountdown2(long millisInFuture) {
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

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

}