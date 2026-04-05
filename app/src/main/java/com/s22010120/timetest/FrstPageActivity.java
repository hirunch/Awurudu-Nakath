package com.s22010120.timetest;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
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
    private View viewAllNakathBtn;
    private ImageButton soundToggleButton;
    private FrameLayout compassBtn;
    private FrameLayout[] countdownFrames;
    private TextView[] countdownViews;
    private CountDownTimer activeCountDownTimer;
    private int currentNakathIndex = -1;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frst_page);

        AudioController.startIfNeeded(this);

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
        viewAllNakathBtn = findViewById(R.id.viewAllNakathBtn);
        soundToggleButton = findViewById(R.id.soundToggleBtn);
        compassBtn = findViewById(R.id.compassBtn);

        updateSoundIcon();
        soundToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean nextMutedState = !AudioController.isMuted(FrstPageActivity.this);
                AudioController.setMuted(FrstPageActivity.this, nextMutedState);
                updateSoundIcon();
            }
        });

        countdownViews = new TextView[]{
                findViewById(R.id.nakathOne),
                findViewById(R.id.nakathTwo),
                findViewById(R.id.nakathThree),
                findViewById(R.id.nakathFour),
                findViewById(R.id.nakathFive),
                findViewById(R.id.nakathSix)
        };

        countdownFrames = new FrameLayout[]{
                findViewById(R.id.framenOne),
                findViewById(R.id.framenTwo),
                findViewById(R.id.framenThree),
                findViewById(R.id.framenFour),
                findViewById(R.id.framenFive),
                findViewById(R.id.framenSix)
        };

        startFromLatestUpcomingNakath();



        viewAllNakathBtn.setOnClickListener(new View.OnClickListener() {
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

    @Override
    protected void onStart() {
        super.onStart();
        AudioController.startIfNeeded(this);
        updateSoundIcon();
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Stop music only when app is exiting from the root screen.
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

    private void startFromLatestUpcomingNakath() {
        int nextIndex = findNextUpcomingIndex(System.currentTimeMillis());
        startCountdownForIndex(nextIndex);
    }

    private int findNextUpcomingIndex(long nowMillis) {
        for (int i = 0; i < NakathSchedule.TIMES.length; i++) {
            long eventTimeMillis = getNakathTimeMillis(i);
            if (eventTimeMillis > nowMillis) {
                return i;
            }
        }
        return -1;
    }

    private long getNakathTimeMillis(int index) {
        try {
            Date date = dateFormat.parse(NakathSchedule.TIMES[index]);
            if (date == null) {
                return -1;
            }
            return date.getTime();
        } catch (Exception e) {
            return -1;
        }
    }

    private void startCountdownForIndex(int index) {
        if (activeCountDownTimer != null) {
            activeCountDownTimer.cancel();
            activeCountDownTimer = null;
        }

        if (index < 0 || index >= countdownFrames.length) {
            currentNakathIndex = -1;
            showOnlyIndex(-1);
            updateUpcomingTitle(-1);
            return;
        }

        long nowMillis = System.currentTimeMillis();
        long targetTimeMillis = getNakathTimeMillis(index);

        if (targetTimeMillis <= nowMillis) {
            startCountdownForIndex(index + 1);
            return;
        }

        currentNakathIndex = index;
        showOnlyIndex(index);
        updateUpcomingTitle(index);

        activeCountDownTimer = new CountDownTimer(targetTimeMillis - nowMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (currentNakathIndex >= 0 && currentNakathIndex < countdownViews.length) {
                    countdownViews[currentNakathIndex].setText(getCountdownText(millisUntilFinished));
                }
            }

            @Override
            public void onFinish() {
                int finishedIndex = currentNakathIndex;
                if (finishedIndex >= 0 && finishedIndex < countdownFrames.length) {
                    countdownFrames[finishedIndex].setVisibility(View.GONE);
                }
                startCountdownForIndex(finishedIndex + 1);
            }
        }.start();
    }

    private void showOnlyIndex(int visibleIndex) {
        for (int i = 0; i < countdownFrames.length; i++) {
            countdownFrames[i].setVisibility(i == visibleIndex ? View.VISIBLE : View.GONE);
        }
    }

    private void updateUpcomingTitle(int index) {
        if (allNakathBtn == null) {
            return;
        }
        if (index < 0 || index >= NakathSchedule.TITLES.length) {
            allNakathBtn.setText("සියලු නැකත් අවසන් වී ඇත");
            return;
        }
        allNakathBtn.setText("මීළඟ නැකත: " + NakathSchedule.TITLES[index]);
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
        if(activeCountDownTimer != null) {
            activeCountDownTimer.cancel();
        }

    }


}