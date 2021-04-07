package com.example.retrofitphpstudent.ResponseModel

import com.example.retrofitphpstudent.ModelClass.User
import com.google.gson.annotations.SerializedName

class FetchStudentAllResponse(
        @SerializedName("user")
        var studentList:ArrayList<User>,
        var error:Int
)
