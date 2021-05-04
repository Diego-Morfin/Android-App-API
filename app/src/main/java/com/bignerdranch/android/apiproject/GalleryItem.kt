package com.bignerdranch.android.apiproject

import com.google.gson.annotations.SerializedName

data class GalleryItem(
    var name:String="",
    var id:String="",
    var status:String="",
    var gender:String="",
    var species:String="",
    @SerializedName("image") var image:String=""
)
