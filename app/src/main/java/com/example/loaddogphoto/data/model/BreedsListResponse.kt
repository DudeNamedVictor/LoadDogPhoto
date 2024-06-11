package com.example.loaddogphoto.data.model

import com.google.gson.annotations.SerializedName
import com.google.maps.android.Status


data class BreedsListResponse(
    @SerializedName("message")
    val message: Map<String, List<String>>,
    @SerializedName("status")
    val status: Status
)