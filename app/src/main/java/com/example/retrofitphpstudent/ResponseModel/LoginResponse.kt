package com.example.retrofitphpstudent.ResponseModel

import com.example.retrofitphpstudent.ModelClass.User

class LoginResponse (
    var user: User,
    var error: Int,
    var message: String
)