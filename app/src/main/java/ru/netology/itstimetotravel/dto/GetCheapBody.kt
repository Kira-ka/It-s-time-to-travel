package ru.netology.itstimetotravel.dto

import com.google.gson.annotations.SerializedName

data class GetCheapBody(
    @SerializedName("startLocationCode")
    val startLocationCode: String = "LED"
)
