package com.example.retrofitphpstudent.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.retrofitphpstudent.R
import com.example.retrofitphpstudent.SharedPrefrence.SharedPrefrenceManager


class DashboardFragment : Fragment() {

    lateinit var sharedPrefrenceManager:SharedPrefrenceManager
    lateinit var txtStudentName:TextView
    lateinit var txtStudentEmail:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txtStudentName = view.findViewById(R.id.txtStudentNameDashboard)
        txtStudentEmail = view.findViewById(R.id.txtStudentEmailDashboard)

        sharedPrefrenceManager = SharedPrefrenceManager(activity!!)


        txtStudentName.text = "Hey,"+sharedPrefrenceManager.getUser().stud_name
        txtStudentEmail.text = sharedPrefrenceManager.getUser().stud_email

    }
}