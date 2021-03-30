package com.example.retrofitphpstudent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.retrofitphpstudent.ResponseModel.RegisterResponse
import com.example.retrofitphpstudent.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mtxtGoToLogin: TextView
    lateinit var mEdtUserName: EditText
    lateinit var mEdtUserEmail: EditText
    lateinit var mEdtUserPassword: EditText
    lateinit var mBtnRegister: Button

    companion object{
        val KEY = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //hide action bar
        supportActionBar!!.hide()

        //hide status bar
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)

        mtxtGoToLogin = findViewById(R.id.txtGoToLoginPage)
        mEdtUserName = findViewById(R.id.edtUserName)
        mEdtUserEmail = findViewById(R.id.edtUserEmail)
        mEdtUserPassword = findViewById(R.id.edtUserPassword)
        mBtnRegister  = findViewById(R.id.btnRegister)

        //Redirect to Login Page
        mtxtGoToLogin.setOnClickListener {
            Toast.makeText(this,"Go to register", Toast.LENGTH_LONG).show()
            Log.d(KEY,"Go to Login Activity")
            var intent = Intent(this@MainActivity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        //perform registration and save data to MySql Database
        mBtnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val userName = mEdtUserName.text.toString()
        val userEmail = mEdtUserEmail.text.toString()
        val userPassword = mEdtUserPassword.text.toString()

        if (userName.isEmpty()){
            mEdtUserName.requestFocus()
            mEdtUserName.error = "Please Enter Name"
            return
        }
        if (userEmail.isEmpty()){
            mEdtUserEmail.requestFocus()
            mEdtUserEmail.error = "Please Enter Email"
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            mEdtUserEmail.requestFocus()
            mEdtUserEmail.error = "Please Enter Correct Email"
            return
        }
        if (userPassword.isEmpty()){
            mEdtUserPassword.requestFocus()
            mEdtUserPassword.error = "Please Enter Email"
            return
        }
        if (userPassword.length<8){
            mEdtUserPassword.requestFocus()
            mEdtUserPassword.error = "Password must have 8 Length"
            return
        }

        var call:Call<RegisterResponse> = RetrofitClient()
            .getInstance()
            .getApi()
            .register(userName,userEmail,userPassword)

        call.enqueue(object:Callback<RegisterResponse>{
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                val registerResponse:RegisterResponse = response.body()!!

                if (response.isSuccessful){
                    Toast.makeText(this@MainActivity,registerResponse.msg, Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this@MainActivity,registerResponse.msg, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d(KEY,t.message.toString())
            }

        })
    }
}