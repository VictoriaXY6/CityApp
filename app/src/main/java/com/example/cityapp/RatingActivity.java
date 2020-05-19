package com.example.cityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//the fifth page
public class RatingActivity extends AppCompatActivity implements View.OnClickListener {

    private City city;
    //list of star buttons
    private ArrayList<ImageButton> stars = new ArrayList<ImageButton>();
    private ImageButton button1;
    private ImageButton button2;
    private ImageButton button3;

    private TextView mTextView;
    private EditText mEditText;
    private TextView save;
    private int rate = 0;
    //open file
    private File file;
    //write into the file
    private FileWriter writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get the content from XML file and render to the phone
        setContentView(R.layout.activity_rating);
        Intent intent = getIntent();
        city = (City) intent.getSerializableExtra("city");
        stars.add((ImageButton) findViewById(R.id.star1));
        stars.add((ImageButton) findViewById(R.id.star2));
        stars.add((ImageButton) findViewById(R.id.star3));
        stars.add((ImageButton) findViewById(R.id.star4));
        stars.add((ImageButton) findViewById(R.id.star5));
        button1 = (ImageButton) findViewById(R.id.previousBtn);
        button2 = (ImageButton) findViewById(R.id.returnBtn);
        button3 = (ImageButton) findViewById(R.id.serializablebtn);
        for(ImageButton button : stars ){
            button.setOnClickListener(this);
        }
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);

        save =findViewById(R.id.save);
        save.setTextColor(0xFF6AEBFF);
        mTextView = findViewById(R.id.wordCount);
        mEditText = findViewById(R.id.userInput);
        //define the Onchange event of EditText
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //when the user inputs some characters, calculate the difference between the
            //maximum character and current user input length
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTextView.setText(String.valueOf(250 - s.length()));
            }

            public void afterTextChanged(Editable s) {

            }
        });
        //file path is located at sdcard
        File path = getExternalFilesDir(null);
        //look for or create the specific file in the specific path
        file = new File(path, "data.txt");
        try {
            //access the file using FileWriter
            writer = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.previousBtn){
            try {
                openBack(city);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(v.getId() == R.id.returnBtn){
            try {
                openFirst();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(v.getId() == R.id.serializablebtn){
            try {
                serialization();
                displayPopup(v, "🦕🦖🦕Successfully Store The Comment & The Rating🐯");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            //check if the star is clicked
            boolean isclick = false;
            //iterate the star buttons from the right to left
            for(int i = 4; i >= 0; i--){
                //if the star button is clicked, set the isClick as true
                if(v.getId() == stars.get(i).getId()){
                    rate = i+1;
                    isclick = true;
                }
                //if the star is clicked, then replace the image as highlight star
                if(isclick){
                    stars.get(i).setImageResource(R.drawable.highlightstar);
                }
                //else replace it as normal
                else{
                    stars.get(i).setImageResource(R.drawable.star);
                }
            }
        }
    }

    public void openBack(City city) throws IOException {
        //close the file
        writer.close();
        Intent intent = new Intent(this, CityVideo2Activity.class);
        intent.putExtra("city", city);
        startActivity(intent);
    }

    public void openFirst() throws IOException {
        writer.close();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //serialize user comment and rating into json format and store to text file
    public void serialization() throws IOException {
        Gson gson = new Gson();
        //serialize user comment and rating into JSON format
        String result = gson.toJson(new UserCommentRating(mEditText.getText().toString(), rate));
        //append the data into file
        writer.append(result+"\n");
        //refresh the file to show the new data user enters
        writer.flush();
    }

    //display the message
    private void displayPopup(View view, String message){
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
