package com.example.retrofitphpstudent.ApiInterface

import com.example.retrofitphpstudent.ResponseModel.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("username") username:String,
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<RegisterResponse>
}