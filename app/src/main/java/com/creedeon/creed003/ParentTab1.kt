package com.creedeon.creed003

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_parent_tab1.*
import java.text.SimpleDateFormat
import java.util.*

class ParentTab1 : androidx.fragment.app.Fragment() {

    internal var date = SimpleDateFormat("dd/MM/yyyy").format(Date())
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Student")
    var sid:String?= String()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parent_tab1, container, false)
    }
    @TargetApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //fragment_date.text = date.toString()

        view.context.getSharedPreferences("shared", Context.MODE_PRIVATE)!!
        sid = arguments!!.getString("id")

        see_attendence.setOnClickListener {
            var intent = Intent(context,student_attendance_sheet::class.java)
            intent.putExtra("id",sid)
            startActivity(intent)
        }

        parent_circular_btn.setOnClickListener {
            startActivity(Intent(it.context,ParentCircularHolder::class.java))
        }

    }
}