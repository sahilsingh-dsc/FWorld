package com.example.fwprld.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;

import java.io.File;
import java.io.IOException;

public class SoloActivity extends AppCompatActivity {
    //---------------------Record---------------//
    private int RECORD_AUDIO_REQUEST_CODE =123 ;
    private MediaRecorder mRecorder;
    private String fileName = null;
   // private int lastProgress = 0;
   // private SeekBar seekBar;
    private Chronometer chronometer;
    //-----------------------------------------------//
    Bundle songBundle;
    String recommended_song_name, recommended_song_singer, song_url;
    Button btnPlaySong;
    TextView txtSongPlayName, txtSongPlaySingerName;
    ImageView imgVisual, imgAudioList;

    MediaPlayer mediaPlayer;
    MediaRecorder mediaRecorder;
    SeekBar volume;

    String songState = "0";
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getPermissionToRecordAudio();
        }
        audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
        volume=(SeekBar)findViewById(R.id.vol);
        volume.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        chronometer = (Chronometer) findViewById(R.id.chronometerTimer);
        chronometer.setBase(SystemClock.elapsedRealtime());
        btnPlaySong = findViewById(R.id.btnPlaySong);
        txtSongPlayName = findViewById(R.id.txtSongPlayName);
        txtSongPlaySingerName = findViewById(R.id.txtSongPlaySingerName);
        imgAudioList = findViewById(R.id.imgAudioList);
        imgAudioList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SoloActivity.this, RecordingListActivity.class));
            }
        });



        songBundle = getIntent().getExtras();
        assert songBundle != null;
        recommended_song_name = songBundle.getString("recommended_song_name");
        recommended_song_singer = songBundle.getString("recommended_song_singer");
        song_url = songBundle.getString("song_url");

        txtSongPlayName.setText(recommended_song_name);
        txtSongPlaySingerName.setText(recommended_song_singer);

        mediaPlayer = new MediaPlayer();

        btnPlaySong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (songState.equals("0")){

                    songState = "1";
                    btnPlaySong.setText("STOP");

                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mediaPlayer.setDataSource(song_url);
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                               // prepareforRecording();
                                startRecording();
                            }
                        });

                        mediaPlayer.prepare();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }else if (songState.equals("1")){
                    songState = "0";
                    btnPlaySong.setText("START");
                    mediaPlayer.pause();
                    stopRecording();
                }

            }
        });



    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getPermissionToRecordAudio() {
        // 1) Use the support library version ContextCompat.checkSelfPermission(...) to avoid
        // checking the build version since Context.checkSelfPermission(...) is only available
        // in Marshmallow
        // 2) Always check for permission (even if permission has already been granted)
        // since the user can revoke permissions at any time through Settings
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ) {

            // The permission is NOT already granted.
            // Check if the user has been asked about this permission already and denied
            // it. If so, we want to give more explanation about why the permission is needed.
            // Fire off an async request to actually get the permission
            // This will show the standard permission request dialog UI
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    RECORD_AUDIO_REQUEST_CODE);

        }
    }

    private void startRecording() {
        //we use the MediaRecorder class to record
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        /**In the lines below, we create a directory named VoiceRecorderSimplifiedCoding/Audios in the phone storage
         * and the audios are being stored in the Audios folder **/
        File root = android.os.Environment.getExternalStorageDirectory();
        File file = new File(root.getAbsolutePath() + "/VoiceRecorderSimplifiedCoding/Audios");
        if (!file.exists()) {
            file.mkdirs();
        }

        fileName =  root.getAbsolutePath() + "/VoiceRecorderSimplifiedCoding/Audios/" + String.valueOf(System.currentTimeMillis() + ".mp3");
        Log.d("filename",fileName);
        mRecorder.setOutputFile(fileName);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //lastProgress = 0;
        //seekBar.setProgress(0);
      //  stopPlaying();
        //starting the chronometer
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }
   /* private void stopPlaying() {
        try{
           mediaPlayer.release();
        }catch (Exception e){
            e.printStackTrace();
        }
        mediaPlayer= null;
        //showing the play button
        imageViewPlay.setImageResource(R.mipmap.ic_launcher);
        chronometer.stop();
    }*/

    private void stopRecording() {

        try{
            mRecorder.stop();
            mRecorder.release();
        }catch (Exception e){
            e.printStackTrace();
        }
        mRecorder = null;
        //starting the chronometer
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        //showing the play button
        Toast.makeText(this, "Recording saved successfully.", Toast.LENGTH_SHORT).show();
    }
}
