package com.s22010120.timetest;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

public final class AudioController {

    private static final String PREFS_NAME = "audio_prefs";
    private static final String KEY_MUTED = "is_muted";

    private static MediaPlayer mediaPlayer;

    private AudioController() {
    }

    public static synchronized void startIfNeeded(Context context) {
        Context appContext = context.getApplicationContext();
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(appContext, R.raw.song1);
            if (mediaPlayer == null) {
                return;
            }
            mediaPlayer.setLooping(true);
        }

        applyMuteState(appContext);

        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    public static synchronized void setMuted(Context context, boolean muted) {
        Context appContext = context.getApplicationContext();
        SharedPreferences preferences = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        preferences.edit().putBoolean(KEY_MUTED, muted).apply();
        applyMuteState(appContext);
    }

    public static synchronized boolean isMuted(Context context) {
        Context appContext = context.getApplicationContext();
        SharedPreferences preferences = appContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(KEY_MUTED, false);
    }

    private static void applyMuteState(Context context) {
        if (mediaPlayer == null) {
            return;
        }
        boolean muted = isMuted(context);
        float volume = muted ? 0f : 1f;
        mediaPlayer.setVolume(volume, volume);
    }

    public static synchronized void release() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

