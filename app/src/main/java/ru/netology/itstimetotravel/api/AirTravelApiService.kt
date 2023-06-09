package ru.netology.itstimetotravel.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import ru.netology.itstimetotravel.BuildConfig
import ru.netology.itstimetotravel.dto.FlightResponse
import ru.netology.itstimetotravel.dto.GetCheapBody

private const val BASE_URL = "${BuildConfig.BASE_URL}/api/avia-service/twirp/aviaapijsonrpcv1.WebAviaService/"

private val logging = HttpLoggingInterceptor().apply {
    if (BuildConfig.DEBUG){
        level = HttpLoggingInterceptor.Level.BODY
    }
}

private val okhhtp = OkHttpClient.Builder()
    .addInterceptor(logging)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .client(okhhtp)
    .build()

interface AirTravelApiService {

    @POST("GetCheap")
    suspend fun getAll(@Body body: GetCheapBody = GetCheapBody()): Response<FlightResponse>

}

object TravelApi {
    val service: AirTravelApiService by lazy {
        retrofit.create(AirTravelApiService::class.java)
    }
}
