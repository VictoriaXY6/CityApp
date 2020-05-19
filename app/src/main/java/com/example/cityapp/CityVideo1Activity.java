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

//the third page
public class CityVideo1Activity extends YouTubeBaseActivity implements View.OnClickListener, YouTubePlayer.OnInitializedListener{

    //list of image buttons
    private ArrayList<ImageButton> buttons = new ArrayList<ImageButton>();
    private Button button1;
    private Button button2;
    private City city;
    //YouTubePlayer class uses to control the video such as pause, move forward/backward
    private YouTubePlayer player;
    //use to connect the app with the video online
    private YouTubePlayerView mYouTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_video1);
        Intent intent = getIntent();
        city = (City) intent.getSerializableExtra("city");
        //connect the video in the layout to the code
        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtubePlay);
        //initialize the video with the API key and begin to play
        mYouTubePlayerView.initialize(PlayerConfig.getApiKey(), this);
        buttons.add((ImageButton) findViewById(R.id.nextBtn));
        buttons.add((ImageButton) findViewById(R.id.returnBtn));
        buttons.add((ImageButton) findViewById(R.id.previousBtn));
        //define the Onclick event for all the image buttons
        for(ImageButton btn : buttons){
            btn.setOnClickListener(this);
        }
        button1 = (Button) findViewById(R.id.videoForwardBtn);
        button2 = (Button) findViewById(R.id.videoBackwardBtn);
        //define the Onclick event such that fast forward the video and display message
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //display the message
                displayPopup(findViewById(R.id.videoForwardBtn), "🐷Move Forward 10 seconds🐷");
                //fast forward video for 10 second
                player.seekRelativeMillis(10000);
            }
        });
        //define the Onclick event such that move backward the video and display message
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayPopup(findViewById(R.id.videoBackwardBtn), "🐷Move Backward 10 seconds🐷");
                //move backward video for 10 second
                player.seekRelativeMillis(-10000);
            }
        });
        //Convert the image to object based on the image id
        ImageView image = (ImageView) findViewById(R.id.img);
        //reset the image based on specific choice
        image.setImageResource(city.getImgRes(1));
    }

    //popup message
    private void displayPopup(View view, String message){
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    //define the Onclick events based on different buttons' id
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

    //move to next page
    public void openNext(City city){
        Intent intent = new Intent(this, CityVideo2Activity.class);
        intent.putExtra("city", city);
        startActivity(intent);
    }

    //move to previous page
    public void openBack(City city){
        Intent intent = new Intent(this, CityDescriptionActivity.class);
        intent.putExtra("city", city);
        startActivity(intent);
    }

    //move to main page
    public void openFirst(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //call when the video is initialized
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        this.player = youTubePlayer;
        //check if there is any error in the video
        if (!b) {
            //load the video base on specific link
            youTubePlayer.loadVideo(city.getVideoURL(0));
            //display message
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
