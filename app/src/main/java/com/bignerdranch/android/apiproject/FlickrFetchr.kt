package com.bignerdranch.android.apiproject

import android.util.Log
import android.widget.Gallery
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.apiproject.api.FlickrApi
import com.bignerdranch.android.apiproject.api.PhotoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val TAG = "FlickrFetchr"

class FlickrFetchr {

    private val flickrApi: FlickrApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/") // https://rickandmortyapi.com/
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        flickrApi = retrofit.create(FlickrApi::class.java)
    }

    fun fetchPhotos(): LiveData<List<GalleryItem>> {
        val responseLiveData: MutableLiveData<List<GalleryItem>> = MutableLiveData()
        val flickrRequest: Call<PhotoResponse> = flickrApi.fetchPhotos() // instead of flickrresponse

        flickrRequest.enqueue(object : Callback<PhotoResponse> {
            override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }

            override fun onResponse(call: Call<PhotoResponse>, response: Response<PhotoResponse>) {
                Log.d(TAG, "Response received")
               //val flickrResponse: PhotoResponse? = response.body()
                val photoResponse: PhotoResponse? = response.body()
                var galleryItems: List<GalleryItem> = photoResponse?.galleryItems ?: mutableListOf() // api follows this structure.
                galleryItems = galleryItems.filterNot { it.image.isBlank() }

                responseLiveData.value = galleryItems
            }
        })

        return responseLiveData
    }
}