package com.example.cityapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


//the second page
public class CityDescriptionActivity extends AppCompatActivity {

    //define the button
    private ImageButton button1;
    private ImageButton button2;
    //record the city which passes from previous page
    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_description);
        Intent intent = getIntent();
        //receive the City object from previous page
        city = (City) intent.getSerializableExtra("city");
        //Connect the ImageView object to the layout through the id
        ImageView img = (ImageView) findViewById(R.id.img);
        //reset the picture of "the image" on top based on the city passes from previous page
        img.setImageResource(city.getImgRes(0));
        //Connect the TextView object to the layout through the id
        TextView title = (TextView) findViewById(R.id.title);
        //reset the text based on the city passes from previous page
        title.setText(city.toString());
        TextView text = (TextView) findViewById(R.id.description);
        text.setText(city.getDescription());
        button1 = (ImageButton) findViewById(R.id.nextBtn);
        //define the Onclick event of button1
        //button1 is used to define the next page behavior
        //based on the city passes from previous page
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (city.getButton()) {
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
        });

        button2 = (ImageButton) findViewById(R.id.returnBtn);
        //set up the Onclickevent of button2
        //button2 defines as return to main page
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFirst();
            }
        });

    }

    //move to next page
    public void openNext(City city){
        Intent intent = new Intent(this, CityVideo1Activity.class);
        intent.putExtra("city", city);
        startActivity(intent);
    }

    //move to the main page
    public void openFirst(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
