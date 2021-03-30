package com.example.retrofitphpstudent.Retrofit

import com.example.retrofitphpstudent.ApiInterface.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitClient {

    val BASE_URL = "http://192.168.225.160/studentApi/"
    var retrofitClient:RetrofitClient? = null
    lateinit var retrofit:Retrofit

    constructor(){
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance():RetrofitClient{
        if (retrofitClient == null){
            retrofitClient = RetrofitClient()
        }

        return retrofitClient as RetrofitClient
    }

    fun getApi():Api{
        return retrofit.create(Api::class.java)
    }
}