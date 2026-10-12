package com.example.projektjson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PlaceHolder {
    @GET("db.json")
    public Call<List<Ogloszenia>> getOgloszenia();
}
