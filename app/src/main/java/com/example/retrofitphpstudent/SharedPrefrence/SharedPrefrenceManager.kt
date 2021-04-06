package com.example.retrofitphpstudent.SharedPrefrence

import android.content.Context
import android.content.SharedPreferences
import com.example.retrofitphpstudent.ModelClass.User

class SharedPrefrenceManager(var context:Context) {
    val SHARED_PREF_NAME = "RetrofitPHPStudent"
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor:SharedPreferences.Editor

    fun saveStudent(user:User){
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putString("id",user.stud_id)
        editor.putString("name",user.stud_name)
        editor.putString("email",user.stud_email)
        editor.putBoolean("logged",true)
        editor.apply()
    }

    fun isLoggedIn():Boolean{
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("logged",false)
    }

    fun getUser():User{
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE)

        return User(
                sharedPreferences.getString("id",null).toString(),
                sharedPreferences.getString("name",null).toString(),
                sharedPreferences.getString("email",null).toString()
        )
    }
}