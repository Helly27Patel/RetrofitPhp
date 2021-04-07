package com.example.retrofitphpstudent.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitphpstudent.ModelClass.User
import com.example.retrofitphpstudent.R

class StudentAdapter(var context:Context,var studentList:ArrayList<User>) : RecyclerView.Adapter<StudentAdapter.studentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter.studentViewHolder {
        var view:View = LayoutInflater.from(context).inflate(R.layout.student_item_layout,parent,false)
        return studentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentAdapter.studentViewHolder, position: Int) {
        holder.txtStudentName.text = studentList[position].stud_name
        holder.txtStudentEmail.text = studentList[position].stud_email
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    class studentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var txtStudentName:TextView = itemView.findViewById(R.id.txtStudentName)
            var txtStudentEmail:TextView = itemView.findViewById(R.id.txtStudentEmail)
    }

}