package com.creedeon.creed003

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_student_login.*



class StudentLogin : AppCompatActivity(){


    var id:String = String()
    var pass:String= String()
    var stu_list_id = ArrayList<String>()
    var stu_list_pass = ArrayList<String>()
    internal var item: String = String()

    private lateinit var progressDialog: ProgressDialog

    val database = FirebaseDatabase.getInstance()




   // var classes :String  = String()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)
        progressDialog = ProgressDialog(this@StudentLogin)


       //val spinner = findViewById<View>(R.id.spinner_student_login) as Spinner


//       spinner.onItemSelectedListener = this
//       val categories = java.util.ArrayList<String>()
//
//       categories.add("S12M")
//       categories.add("S12B")
//
//
//       val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
//       dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//       spinner.adapter = dataAdapter

       val myRef = database.getReference("Student")


       student_login_button.setOnClickListener {
            progressDialog.setTitle("Logging in...")
            progressDialog.show()

            myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    p0.children.forEach {
                        stu_list_id.add(it.child("sid").getValue(String::class.java)!!)
                        stu_list_pass.add(it.child("spass").getValue(String::class.java)!!)
                    }
                    if (student_login_id.text.toString() == "" || student_login_pass.text.toString() == "") {
                        progressDialog.dismiss()

                        if (student_login_id.text.toString() == "") {
                            student_login_id.error = "Please enter the id !!"
                        }
                        if (student_login_pass.text.toString() == "") {
                            student_login_pass.error = "Please enter the password !!"
                        }
                    } else {

                            stu_list_id.forEachIndexed { index, s ->
                            Log.d("Check", s)
                            if (s == student_login_id.text.toString().trim()) {
                                Log.d("Check", student_login_id.text.toString() + " " + student_login_pass.text.toString())
                                Log.d("Check", s)
                                if (stu_list_pass[index] == student_login_pass.text.toString().trim()) {
                                    Log.d("Check", student_login_id.text.toString() + " " + student_login_pass.text.toString())
                                    Log.d("Check", stu_list_pass[index])

                                    val intent = Intent(this@StudentLogin, ParentActivity::class.java)
                                    val shared:SharedPreferences = getSharedPreferences("shared", Context.MODE_PRIVATE)
                                    val editor:SharedPreferences.Editor = shared.edit()
                                    editor.putString("stuid",s)
                                    editor.putString("stupass",stu_list_pass[index])
                                    editor.commit()
                                    intent.putExtra("id", student_login_id.text.toString().trim())
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
