package ru.netology.itstimetotravel.repository

import kotlinx.coroutines.flow.Flow
import ru.netology.itstimetotravel.dto.Plain

interface FlightRepository {
    val data: Flow<List<Plain>>
    suspend fun getAll()
    fun likeById(id: Long)
}
