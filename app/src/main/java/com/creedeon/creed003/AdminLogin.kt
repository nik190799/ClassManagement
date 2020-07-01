package com.creedeon.creed003

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_admin_login.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AdminLogin : AppCompatActivity() {
    var admin_id_list = ArrayList<String>()
    var admin_pass_list = ArrayList<String>()
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Admin")


    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        progressDialog = ProgressDialog(this@AdminLogin)
        admin_login_button.setOnClickListener {

            progressDialog.setTitle("Logging in...")
            progressDialog.show()


            myRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    p0.children.forEach {
                        admin_id_list.add(it.child("id").getValue(String::class.java)!!)
                        admin_pass_list.add(it.child("pass").getValue(String::class.java)!!)
                    }
                    if (admin_id.text.toString() == "" || admin_password.text.toString() == "") {
                        if (admin_id.text.toString() == "") {
                            progressDialog.dismiss()
                            admin_password.error = "Please enter the id !!"
                        }
                        if (admin_password.text.toString() == "") {
                            progressDialog.dismiss()
                            admin_password.error = "Please enter the pass !!"
                        }
                    } else {

                        admin_id_list.forEachIndexed { index, s ->
                            Log.d("Check", s)
                            if (s == admin_id.text.toString().trim()) {
                                Log.d("Check", admin_id.text.toString() + " " + admin_password.text.toString())
                                Log.d("Check", s)
                                if (admin_pass_list[index] == admin_password.text.toString().trim()) {
                                    Log.d("Check", admin_id.text.toString() + " " + admin_password.text.toString())
                                    Log.d("Check", admin_pass_list[index])


                                    var intent = Intent(this@AdminLogin, AdminActivity::class.java)
                                    var Adminshared = getSharedPreferences("Adminshared", Context.MODE_PRIVATE)
                                    var editor: SharedPreferences.Editor = Adminshared.edit()
                                    editor.putString("Adminid",s)
                                    editor.putString("Adminpass",admin_pass_list[index])
                                    editor.apply()
                                    intent.putExtra("id", admin_id.text.toString().trim())
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


