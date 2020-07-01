package com.creedeon.creed003

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_parent_tab3.*
import java.text.SimpleDateFormat
import java.util.*


class ParentTab3 : androidx.fragment.app.Fragment() {

    internal var date = SimpleDateFormat("dd MMMM yyyy").format(Date())
    var sid:String= String()

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Student")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parent_tab3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_date.text = date.toString()

        sid = arguments?.getString("id").toString()



        homework_btn.setOnClickListener {

            var intent = Intent(it.context,ParentHomeworkHolder::class.java)
            intent.putExtra("id",sid)
            startActivity(intent)
        }


        parent_payment_btn.setOnClickListener {

            var intent = Intent(it.context, ParentPayment::class.java)
            intent.putExtra("id",sid)
            startActivity(intent)
        }


    }


}