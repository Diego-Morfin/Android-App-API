package com.bignerdranch.android.apiproject.api

import com.bignerdranch.android.apiproject.GalleryItem
import com.google.gson.annotations.SerializedName

class PhotoResponse {
    @SerializedName("results")
    lateinit var galleryItems: List<GalleryItem> // this will contain the list of pictures
}