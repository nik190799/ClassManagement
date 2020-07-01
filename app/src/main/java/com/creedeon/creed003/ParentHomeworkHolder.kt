package com.creedeon.creed003

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.parent_homework_holder.*



class ParentHomeworkHolder:AppCompatActivity(){


    val database = FirebaseDatabase.getInstance()

    val homework:ArrayList<HomeworkItem> = ArrayList()

    var classes:String = String()



    val myref = database.getReference("Homework")

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_homework_holder)


        var s_id = intent.getStringExtra("id")


        if (s_id[3].equals('M')){
            classes = "S12M"
        }
        else if(s_id[3].equals('B')){
            classes = "S12B"
        }


        progressDialog = ProgressDialog(this@ParentHomeworkHolder)
        progressDialog.setTitle("Loading...")
        progressDialog.show()


        myref.child(classes).addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach {


                        homework.add(
                            HomeworkItem(
                                it.child("cname").getValue(String::class.java)!!,
                                it.child("tname").getValue(String::class.java)!!,
                                it.child("date").getValue(String::class.java)!!,
                                it.child("postId").getValue(String::class.java)!!,
                                it.child("sclass").getValue(String::class.java)!!,
                                it.child("teacher").getValue(String::class.java)!!)
                        )


                }
                homework.reverse()
                homework.forEach{
                    adapter.add(it)
                }
                homework_holder_recyclerview_pt3.adapter = adapter

                progressDialog.dismiss()
            }

        })

    }

}