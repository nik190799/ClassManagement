package com.creedeon.creed003

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.show_homework.*

class ShowHomework :AppCompatActivity() {


    val database = FirebaseDatabase.getInstance()
    val myref = database.getReference("ST1")
    val homework:ArrayList<HomeworkItem1> =  ArrayList()

    private lateinit var progressDialog: ProgressDialog

    @TargetApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_homework)

        progressDialog = ProgressDialog(this@ShowHomework)

        progressDialog.setTitle("Loading...")
        progressDialog.show()

        myref.addListenerForSingleValueEvent(object:ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach{
                    homework.add(HomeworkItem1(it.child("cname").getValue(String::class.java)!!,
                                               it.child("tname").getValue(String::class.java)!!,
                                               it.child("date").getValue(String::class.java)!!,
                                               it.child("postId").getValue(String::class.java)!!))

                }
                homework.reverse()
                homework.forEach{
                    adapter.add(it)
                }

                recyclerview_showhomework.adapter = adapter
                progressDialog.dismiss()

            }

        })
    }
}

