package com.creedeon.creed003

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_teacher_tab1.*
import java.text.SimpleDateFormat
import java.util.*


class TeacherTab1 : androidx.fragment.app.Fragment() {

    internal var date = SimpleDateFormat("dd/MM/yyyy").format(Date())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teacher_tab1, container, false)
    }
    @TargetApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // fragment_date.text = date.toString()

        teacher_circular_btn.setOnClickListener {
            startActivity(Intent(it.context,TeacherCircular::class.java))
        }

        admin_msg_btn.setOnClickListener {
            startActivity(Intent(it.context,TeacherAdminCircular::class.java))
        }


    }
}
