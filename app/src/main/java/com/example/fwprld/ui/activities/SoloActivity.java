package com.example.fwprld.ui.activities;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fwprld.R;

import java.io.IOException;

public class SoloActivity extends AppCompatActivity {

    Bundle songBundle;
    String recommended_song_name, recommended_song_singer, song_url;
    Button btnPlaySong;
    TextView txtSongPlayName, txtSongPlaySingerName;
    ImageView imgVisual;

    MediaPlayer mediaPlayer;
    MediaRecorder mediaRecorder;

    private static final String LOG_TAG = "AudioRecording";
    private static String mFileName = null;
    public static final int REQUEST_AUDIO_PERMISSION_CODE = 1;

    String songState = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo);

        btnPlaySong = findViewById(R.id.btnPlaySong);
        txtSongPlayName = findViewById(R.id.txtSongPlayName);
        txtSongPlaySingerName = findViewById(R.id.txtSongPlaySingerName);


        songBundle = getIntent().getExtras();
        assert songBundle != null;
        recommended_song_name = songBundle.getString("recommended_song_name");
        recommended_song_singer = songBundle.getString("recommended_song_singer");
        song_url = songBundle.getString("song_url");

        txtSongPlayName.setText(recommended_song_name);
        txtSongPlaySingerName.setText(recommended_song_singer);

        mediaPlayer = new MediaPlayer();

//        mFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
//        mFileName += "/AudioRecording.3gp";
//
//        mediaRecorder = new MediaRecorder();
//        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//        mediaRecorder.setOutputFile(mFileName);


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
                }

            }
        });



    }



}
