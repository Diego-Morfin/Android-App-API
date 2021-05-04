package com.bignerdranch.android.apiproject.api
import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {

    @GET(
        "api/character"
    )
    fun fetchPhotos(): Call<PhotoResponse> // calls the outermost layer of JSON
}