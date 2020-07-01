package com.creedeon.creed003

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_public_login.*
import java.text.SimpleDateFormat
import java.util.*

class PublicLogin : AppCompatActivity() {

   @TargetApi(Build.VERSION_CODES.O)

    val database = FirebaseDatabase.getInstance()
    val myRef= database.getReference("Public User")

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_login)

        progressDialog = ProgressDialog(this@PublicLogin)
        public_login_btn.setOnClickListener {

            progressDialog.setTitle("Logging in...")
            progressDialog.show()

            if(public_name.text.toString()=="" || public_email.text.toString()=="" || public_phone.text.toString()=="" || public_address.text.toString()==""){
                progressDialog.dismiss()
                Toast.makeText(this,"Please enter the details",Toast.LENGTH_LONG).show()
            }
            else{

                val date = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(Date())


                val puser = PublicUser(public_name.text.toString(),
                                       public_email.text.toString(),
                                       public_phone.text.toString(),
                                       public_address.text.toString(),
                                       date)


                myRef.push().setValue(puser)
                public_name.setText("")
                public_email.setText("")
                public_phone.setText("")
                public_address.setText("")

                Toast.makeText(this,"Welcome To JK Academy",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,ClassInfo::class.java))
                progressDialog.dismiss()

            }

        }

    }



}