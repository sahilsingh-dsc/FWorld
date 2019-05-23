package com.example.fwprld.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.fwprld.R;

public class SoloVideoActivity extends AppCompatActivity {

    Bundle songBundle;
    String recommended_song_name, recommended_song_singer, song_url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_video);

        songBundle = getIntent().getExtras();
        assert songBundle != null;
        recommended_song_name = songBundle.getString("recommended_song_name");
        recommended_song_singer = songBundle.getString("recommended_song_singer");
        song_url = songBundle.getString("song_url");



    }

}
