package com.creedeon.creed003

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.teacher_circular.*

class TeacherCircular:AppCompatActivity(){

    val database = FirebaseDatabase.getInstance()
    val myref = database.getReference("Circular")
    val files:ArrayList<FileHolderClass> =  ArrayList()

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_circular)
        progressDialog = ProgressDialog(this@TeacherCircular)


        progressDialog.setTitle("Loading...")
        progressDialog.show()

        myref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()

                p0.children.forEach{
                    var filepath :String = it.child("file_path").getValue(String::class.java)!!
                    var filename :String = it.child("file_name").getValue(String::class.java)!!
                    var date:String = it.child("date").getValue(String::class.java)!!
                    var postId:String = it.child("postId").getValue(String::class.java)!!
                    Log.d("file",filename+"\n"+filepath)

                    var file = FileHolderClass(filename,filepath,date,postId)

                    files.add(file)

                }
                files.reverse()
                files.forEach {
                    adapter.add(it)
                }

                adapter.setOnItemClickListener { item, view ->
                    var file = item as FileHolderClass
                    Log.d("filepath", file.file_path)
                    if(file.file_path!="") {
                        var uri: Uri = Uri.parse(file.file_path)
                        var intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    }else{
                        Toast.makeText(view.context,"File is not attached...", Toast.LENGTH_SHORT).show()
                    }
                }
                recyclerview_teacher_tab.adapter = adapter
                progressDialog.dismiss()
            }

        })
    }

}