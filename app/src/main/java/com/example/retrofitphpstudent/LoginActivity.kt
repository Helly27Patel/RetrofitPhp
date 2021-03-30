package com.example.retrofitphpstudent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginActivity : AppCompatActivity() {

    lateinit var mEdtUserLoginEmail: EditText
    lateinit var mEdtUserLoginPassword: EditText
    lateinit var mBtnLogin: Button
    lateinit var mTxtGoToRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide action bar
        supportActionBar!!.hide()

        //hide status bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        mTxtGoToRegister  =findViewById(R.id.txtGoToRegisterPage)
        mEdtUserLoginEmail  =findViewById(R.id.edtUserLoginEmail)
        mEdtUserLoginPassword  =findViewById(R.id.edtUserLoginPassword)
        mBtnLogin = findViewById(R.id.btnLogin)

        //Redirect user to Register Page
        mTxtGoToRegister.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}