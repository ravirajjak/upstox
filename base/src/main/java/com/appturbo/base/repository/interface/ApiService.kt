package com.appturbo.base.repository.`interface`

import com.appturbo.base.repository.model.PortfolioData
import com.appturbo.base.repository.model.PortfolioModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    @POST("v3/6d0ad460-f600-47a7-b973-4a779ebbaeaf")
    suspend fun getPortfolio(): Response<PortfolioModel>

    companion object {

        fun create(): ApiService {

            val interceptorBody = HttpLoggingInterceptor()
            interceptorBody.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptorBody)
                .build()


            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://run.mocky.io/")
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}