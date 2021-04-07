package com.example.retrofitphpstudent.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitphpstudent.Adapter.StudentAdapter
import com.example.retrofitphpstudent.ModelClass.User
import com.example.retrofitphpstudent.R
import com.example.retrofitphpstudent.ResponseModel.FetchStudentAllResponse
import com.example.retrofitphpstudent.Retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UsersFragment : Fragment() {

    lateinit var studentRecyclerView:RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        studentRecyclerView = view.findViewById(R.id.studentRecyclerView)

        var call:Call<FetchStudentAllResponse> = RetrofitClient()
                .getInstance()
                .getApi()
                .fetchAllStudent()

        call.enqueue(object:Callback<FetchStudentAllResponse>{
            override fun onResponse(call: Call<FetchStudentAllResponse>, response: Response<FetchStudentAllResponse>) {
                if (response.isSuccessful){
                    var studentResponse:FetchStudentAllResponse = response.body()!!

                    if (studentResponse.error == 0){
                        var studentList:ArrayList<User> = studentResponse.studentList

                        studentRecyclerView.adapter = StudentAdapter(activity!!,studentList)
                    }else{
                        Toast.makeText(activity,"No Data Found",Toast.LENGTH_LONG).show()
                    }
                }
            }

            override fun onFailure(call: Call<FetchStudentAllResponse>, t: Throwable) {
                Toast.makeText(activity,t.message.toString(),Toast.LENGTH_LONG).show()
            }

        })
    }
}