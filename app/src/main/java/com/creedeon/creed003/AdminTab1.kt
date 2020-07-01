package com.creedeon.creed003

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_admin_tab1.*
import java.text.SimpleDateFormat
import java.util.*

class AdminTab1 : androidx.fragment.app.Fragment() {

    internal var date = SimpleDateFormat("dd/MM/yyyy").format(Date())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_admin_tab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //AdminTest()
        // /* var testTab = AdminTest() <- testTab never used error */

        //fragment_date.text = date.toString()
        btn.setOnClickListener {
            val intent = Intent(context,AdminCircular::class.java)
            startActivity(intent)
        }
        chooser.setOnClickListener {
            val intent = Intent(context,UploaderActivity::class.java)
            startActivity(intent)
        }
        show_teacher_circular_btn.setOnClickListener {
            startActivity(Intent(context,TShowTCircular::class.java))
        }


    }
}