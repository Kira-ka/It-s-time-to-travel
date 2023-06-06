package ru.netology.itstimetotravel.dto

import com.google.gson.annotations.SerializedName

data class FlightResponse(
    @SerializedName("flights")
    val flights: List<Plain>
)
