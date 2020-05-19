package com.example.cityapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;

//the first/main page
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //store the list of image buttons
    private ArrayList<ImageButton> buttons = new ArrayList<ImageButton>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //iterate every object that define in enum class
        for(City city : City.values()){
            //connect the image buttons on the layout to java code
            //so we can define their behaviors or their actions later
            buttons.add((ImageButton) findViewById(city.getButton()));
        }
        //iterate all the buttons and set up Onclick events
        for(ImageButton button: buttons) {
            //define all the button actions
            button.setOnClickListener(this);
        }
    }

    //define the button's Onclick event
    @Override
    public void onClick(View v){
        //set up switch statement for each button
        //if the buttons click, call the openNext method
        switch(v.getId()) {
            case R.id.beijingBtn:
                openNext(City.Beijing);
                break;
            case R.id.shanghaiBtn:
                openNext(City.Shanghai);
                break;
            case R.id.shenzhenBtn:
                openNext(City.Shenzhen);
                break;
            case R.id.guangzhouBtn:
                openNext(City.Guangzhou);
                break;
        }
    }

    //change to next page
    public void openNext(City city){
        //define next page
        Intent intent = new Intent(this, CityDescriptionActivity.class);
        //Also pass a city object to next page
        intent.putExtra("city", city);
        //move to next page
        startActivity(intent);
    }
}
