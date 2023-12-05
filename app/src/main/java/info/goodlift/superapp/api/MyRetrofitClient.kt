package info.goodlift.superapp.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MyRetrofitClient {
    private const val BASE_URL = "http://boris.cdu.edu.ua/api/"

    fun getClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
}