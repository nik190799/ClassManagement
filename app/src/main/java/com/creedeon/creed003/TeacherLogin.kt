package com.creedeon.creed003

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_teacher_login.*

class TeacherLogin : AppCompatActivity() {
    var teacher_id_list = ArrayList<String>()
    var teacher_pass_list = ArrayList<String>()
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Users")

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_login)

        progressDialog = ProgressDialog(this@TeacherLogin)

        teacher_login_button.setOnClickListener {

            progressDialog.setTitle("Logging in...")
            progressDialog.show()

            myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    p0.children.forEach {
                        teacher_id_list.add(it.child("id").getValue(String::class.java)!!)
                        teacher_pass_list.add(it.child("pass").getValue(String::class.java)!!)
                    }
                    if (teacher_login_id.text.toString() == "" || teacher_login_pass.text.toString() == "") {
                        progressDialog.dismiss()
                        if (teacher_login_id.text.toString() == "") {
                            teacher_login_id.error = "Please enter the id !!"
                        }
                        if (teacher_login_pass.text.toString() == "") {
                            teacher_login_pass.error = "Please enter the pass !!"
                        }
                    } else {


                        teacher_id_list.forEachIndexed { index, s ->
                            Log.d("Check", s)
                            if (s == teacher_login_id.text.toString().trim()) {
                                Log.d("Check", teacher_login_id.text.toString() + " " + teacher_login_pass.text.toString())
                                Log.d("Check", s)
                                if (teacher_pass_list[index] == teacher_login_pass.text.toString().trim()) {
                                    Log.d("Check", teacher_login_id.text.toString() + " " + teacher_login_pass.text.toString())
                                    Log.d("Check", teacher_pass_list[index])
                                    var intent = Intent(this@TeacherLogin, TeacherActivity::class.java)
                                    var Teachershared = getSharedPreferences("Teachershared", Context.MODE_PRIVATE)
                                    var editor: SharedPreferences.Editor = Teachershared.edit()
                                    editor.putString("Teacherid",s)
                                    editor.putString("Teacherpass",teacher_pass_list[index])
                                    editor.apply()
                                    intent.putExtra("id", teacher_login_id.text.toString().trim())
                                    startActivity(intent)
                                    progressDialog.dismiss()
                                }
                            }
                        }

                        progressDialog.dismiss()

                    }
                }

            })
        }

    }
}
