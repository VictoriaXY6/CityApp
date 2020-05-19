package com.example.cityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

//the fourth page
public class CityVideo2Activity extends YouTubeBaseActivity implements View.OnClickListener, YouTubePlayer.OnInitializedListener{

    private ArrayList<ImageButton> buttons = new ArrayList<ImageButton>();
    private Button button1;
    private Button button2;
    private City city;
    private YouTubePlayer player;
    private YouTubePlayerView mYouTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_video2);
        Intent intent = getIntent();
        city = (City) intent.getSerializableExtra("city");
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlay);
        mYouTubePlayerView.initialize(PlayerConfig.getApiKey(), this);
        buttons.add((ImageButton) findViewById(R.id.nextBtn));
        buttons.add((ImageButton) findViewById(R.id.returnBtn));
        buttons.add((ImageButton) findViewById(R.id.previousBtn));
        for(ImageButton btn : buttons){
            btn.setOnClickListener(this);
        }
        button1 = (Button) findViewById(R.id.videoForwardBtn);
        button2 = (Button) findViewById(R.id.videoBackwardBtn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopup(findViewById(R.id.videoForwardBtn), "🐷Move Forward 10 seconds🐷");
                player.seekRelativeMillis(10000);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopup(findViewById(R.id.videoBackwardBtn), "🐷Move Backward 10 seconds🐷");
                player.seekRelativeMillis(-10000);
            }
        });
        ImageView image = (ImageView) findViewById(R.id.img);
        image.setImageResource(city.getImgRes(2));
    }

    private void displayPopup(View view, String message){
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nextBtn:
                openNext(city);
                break;
            case R.id.previousBtn:
                openBack(city);
                break;
            case R.id.returnBtn:
                openFirst();
                break;
        }
    }

    public void openNext(City city){
        Intent intent = new Intent(this, RatingActivity.class);
        intent.putExtra("city", city);
        startActivity(intent);
    }

    public void openBack(City city){
        Intent intent = new Intent(this, CityVideo1Activity.class);
        intent.putExtra("city", city);
        startActivity(intent);
    }

    public void openFirst(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        this.player = youTubePlayer;
        if (!b) {
            youTubePlayer.loadVideo(city.getVideoURL(1));
            displayPopup(findViewById(android.R.id.content), "🐷🐷Let's Begin the Video🐷🐷");
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
