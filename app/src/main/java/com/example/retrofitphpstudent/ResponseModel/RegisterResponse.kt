package com.example.retrofitphpstudent.ResponseModel

import com.google.gson.annotations.SerializedName

class RegisterResponse(
    var error:Int,
    @SerializedName("message")
    var msg:String
)