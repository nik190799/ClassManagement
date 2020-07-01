package com.creedeon.creed003

import android.app.ProgressDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*


class Profile : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Student")
    var s_id:String = String()

    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        progressDialog = ProgressDialog(this@Profile)

        progressDialog.setTitle("Loading...")
        progressDialog.show()

        s_id = intent.getStringExtra("id")
        var query: Query = myRef.orderByChild("sid").equalTo(s_id)
        query.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach{
                    profile_name.text = it.child("sname").getValue(String::class.java)
                    profile_batch.text = it.child("classes").getValue(String::class.java)
                    progressDialog.dismiss()
                }
            }
        })
        student_id_text.text= s_id

    }

}


