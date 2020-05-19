package com.example.cityapp;

import com.google.gson.annotations.SerializedName;

//POJO class, use to store user comment and rating
public class UserCommentRating {
    @SerializedName("❤️Comment❤️")
    private String comment;
    @SerializedName("❤️Rating❤️")
    private int rating;
    public UserCommentRating(String comment, int rating){
        this.comment = comment;
        this.rating = rating;
    }

    public String getComment(){
        return this.comment;
    }

    public int getRating(){
        return this.rating;
    }
}
