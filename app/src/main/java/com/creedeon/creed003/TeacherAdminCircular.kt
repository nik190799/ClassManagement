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
import kotlinx.android.synthetic.main.teacher_admin_circular.*

class TeacherAdminCircular:AppCompatActivity(){

    val database = FirebaseDatabase.getInstance()
    val myref = database.getReference("Teacher Circular")
    val teacherCircular:ArrayList <TeacherAdminCircularItem> = ArrayList()
    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_admin_circular)

        progressDialog = ProgressDialog(this@TeacherAdminCircular)
        progressDialog.setTitle("Loading...")
        progressDialog.show()

        myref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach{
                    teacherCircular.add(TeacherAdminCircularItem(
                        it.child("cname").getValue(String::class.java)!!,
                        it.child("date").getValue(String::class.java)!!,
                        it.child("postId").getValue(String::class.java)!!))
                }
                teacherCircular.reverse()
                teacherCircular.forEach{
                    adapter.add(it)
                }
                recyclerview_teacher_admin_tab.adapter = adapter
                progressDialog.dismiss()
            }

        })
    }
}