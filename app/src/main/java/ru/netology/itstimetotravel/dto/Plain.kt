package ru.netology.itstimetotravel.dto

data class Plain(
    val id: Long = 0,
    val from: String = "",
    val to: String= "",
    val departureDate: Long = 0,
    val returnDate: Long = 0,
    val price : Int = 0,
    val likedByMe: Boolean = false
)
