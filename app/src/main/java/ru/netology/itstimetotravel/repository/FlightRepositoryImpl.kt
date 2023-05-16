package ru.netology.itstimetotravel.repository


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.netology.itstimetotravel.api.TravelApi
import ru.netology.itstimetotravel.dao.PlainDao
import ru.netology.itstimetotravel.dto.Plain
import ru.netology.itstimetotravel.entity.PlainEntity
import ru.netology.itstimetotravel.entity.toDto
import ru.netology.itstimetotravel.entity.toEntity
import java.io.IOException

val a = Plain(1, "Москва", "Волгоград", 121212,131313, 1000)
val b = Plain(2, "Волгоград", "Москва", 123456,654321, 2000)
val c = listOf(a, b)
class FlightRepositoryImpl(private val dao: PlainDao) : FlightRepository {

    override val data: Flow<List<Plain>> = dao.getAll()
        .map(List<PlainEntity>::toDto)
        .flowOn(Dispatchers.Default)

    override suspend fun getAll() {
       dao.insert(c.toEntity())
//        try {
//            val responce = TravelApi.service.getAll()
//            if (!responce.isSuccessful) {
//                throw RuntimeException(responce.message())
//            }
//
//            val body = responce.body() ?: throw RuntimeException("body is null")
//            dao.insert(body.toEntity())
//        } catch (e: IOException) {
//            throw RuntimeException("network error")
//        } catch (e: Exception) {
//            throw RuntimeException("unknown error")
//        }
    }

    override fun likeById(id: Long) {
        dao.likeById(id)
    }



}
