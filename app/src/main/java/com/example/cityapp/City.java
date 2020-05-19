package com.example.cityapp;

import java.io.Serializable;

//define all the cities
public enum City implements Serializable {
    Beijing (R.id.beijingBtn, new int[]{R.drawable.beijing_a, R.drawable.beijing_b, R.drawable.beijing_c}, CityDescription.beijing, new String[]{"b3-v_QekswE", "8DSZifcUA-Q"}),
    Shanghai (R.id.shanghaiBtn, new int[]{R.drawable.shanghai_a, R.drawable.shanghai_b, R.drawable.shanghai_c}, CityDescription.shanghai, new String[]{"R1lowN2Vkl8", "lnAtDgeWhnc"}),
    Shenzhen (R.id.shenzhenBtn, new int[]{R.drawable.shenzhen_a, R.drawable.shenzhen_b, R.drawable.shenzhen_c}, CityDescription.shenzhen, new String[]{"Rh9OAUotCjc", "cNpl3Dnqb7g"}),
    Guangzhou (R.id.guangzhouBtn, new int[]{R.drawable.guangzhou_a, R.drawable.guangzhou_b, R.drawable.guangzhou_c}, CityDescription.guangzhou, new String[]{"Yd9nFq8MtKU", "85UT63NR9Eo"});

    //Record the City info
    private int button;
    private String textDescription;
    private int[] imgRes;
    private String[] videoURL;

    City(int button, int[] imgRes, String textDescription, String[] videoURL){
        this.button = button;
        this.imgRes = imgRes;
        this.textDescription = textDescription;
        this.videoURL = videoURL;
        this.imgRes = imgRes;
    }

    String getDescription(){
        return this.textDescription;
    }

    //get the button id
    int getButton(){ return button;}

    //get the specific image id
    int getImgRes(int num){
        return imgRes[num];
    }

    //get the specific video link
    String getVideoURL(int num){
        return videoURL[num];
    }


}
