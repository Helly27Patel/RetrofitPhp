package com.example.retrofitphpstudent.Fragment

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.retrofitphpstudent.R
import com.example.retrofitphpstudent.ResponseModel.LoginResponse
import com.example.retrofitphpstudent.Retrofit.RetrofitClient
import com.example.retrofitphpstudent.SharedPrefrence.SharedPrefrenceManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    lateinit var edtUserName:EditText
    lateinit var edtUserEmail:EditText
    lateinit var btnUpdate:Button
    lateinit var sharedPrefrenceManager:SharedPrefrenceManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtUserName= view.findViewById(R.id.edtUserNameProfile)
        edtUserEmail= view.findViewById(R.id.edtUserEmailProfile)
        btnUpdate = view.findViewById(R.id.btnUpdate)

        sharedPrefrenceManager = SharedPrefrenceManager(activity!!)

        var studId:Int = sharedPrefrenceManager.getUser().stud_id.toInt()

        btnUpdate.setOnClickListener {
            updateUserInfo(studId)
        }
    }

    private fun updateUserInfo(id:Int) {
        val userEmail = edtUserEmail.text.toString()
        val userName = edtUserName.text.toString()
        if (userEmail.isEmpty()){
            edtUserEmail.requestFocus()
            edtUserEmail.error = "Please Enter Email"
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            edtUserEmail.requestFocus()
            edtUserEmail.error = "Please Enter Correct Email"
            return
        }
        if (userName.isEmpty()){
            edtUserName.requestFocus()
            edtUserName.error = "Please Enter Email"
            return
        }

        var call:Call<LoginResponse> = RetrofitClient()
                .getInstance()
                .getApi()
                .updateStudent(id,userName,userEmail)

        call.enqueue(object :Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                var updateResponse:LoginResponse = response.body()!!

                if (response.isSuccessful){
                    if (updateResponse.error == 0){
                        sharedPrefrenceManager.saveStudent(updateResponse.user)
                        Toast.makeText(activity,updateResponse.message,Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(activity,updateResponse.message,Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(activity,t.message,Toast.LENGTH_LONG).show()
            }
        })
        
    }
}