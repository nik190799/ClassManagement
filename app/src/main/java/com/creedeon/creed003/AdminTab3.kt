package com.creedeon.creed003

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_admin_tab_3.*
import java.text.SimpleDateFormat
import java.util.*

class AdminTab3 : androidx.fragment.app.Fragment() {

    internal var date = SimpleDateFormat("dd MMMM yyyy").format(Date())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_admin_tab_3, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_date.text = date.toString()

        attendance_btn.setOnClickListener {
            startActivity(Intent(context,Attendance1::class.java))
        }
        public_user_btn.setOnClickListener {
            startActivity(Intent(it.context,PUserHolder::class.java))
        }

        payment_btn.setOnClickListener {
            startActivity(Intent(it.context,APay::class.java))
        }
    }

}