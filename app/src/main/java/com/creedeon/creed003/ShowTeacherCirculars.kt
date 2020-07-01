package com.creedeon.creed003

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.show_teacher_circular.*

class ShowTeacherCirculars:AppCompatActivity(){

    val database = FirebaseDatabase.getInstance()
    val myref = database.getReference("Teacher Circular")
    val teacherCircular:ArrayList <TeacherAdminCircularItem1> = ArrayList()

    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_teacher_circular)


        progressDialog = ProgressDialog(this@ShowTeacherCirculars)
        progressDialog.setTitle("Loading...")
        progressDialog.show()

        myref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()

                p0.children.forEach{

                    teacherCircular.add(TeacherAdminCircularItem1(
                        it.child("cname").getValue(String::class.java)!!,
                        it.child("date").getValue(String::class.java)!!,
                        it.child("postId").getValue(String::class.java)!!))
                }

                teacherCircular.reverse()
                teacherCircular.forEach{
                    adapter.add(it)
                }



                recyclerview_teacher_admin_admin_tab.adapter = adapter
                progressDialog.dismiss()


            }

        })

    }
}