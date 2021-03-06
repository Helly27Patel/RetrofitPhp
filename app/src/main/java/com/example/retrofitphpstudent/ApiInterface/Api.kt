package com.example.retrofitphpstudent.ApiInterface

import com.example.retrofitphpstudent.ResponseModel.FetchStudentAllResponse
import com.example.retrofitphpstudent.ResponseModel.LoginResponse
import com.example.retrofitphpstudent.ResponseModel.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("username") username:String,
        @Field("email") email:String,
        @Field("password") password:String
    ):Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login.php")
    fun login(
            @Field("email") email:String,
            @Field("password") password:String
    ):Call<LoginResponse>


    @GET("fetchstudent.php")
    fun fetchAllStudent():Call<FetchStudentAllResponse>

    @FormUrlEncoded
    @POST("updateuser.php")
    fun updateStudent(
            @Field("studid") id:Int,
            @Field("studname") name:String,
            @Field("studemail") email:String
    ):Call<LoginResponse>
}