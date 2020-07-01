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
import kotlinx.android.synthetic.main.public_user_holder.*

class PUserHolder : AppCompatActivity(){

    val database = FirebaseDatabase.getInstance()
    val myref = database.getReference("Public User")
    val puser:ArrayList<PublicUser> = ArrayList()

    private lateinit var progressDialog: ProgressDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.public_user_holder)

        progressDialog = ProgressDialog(this@PUserHolder)
        progressDialog.setTitle("Loading...")
        progressDialog.show()

        myref.addListenerForSingleValueEvent(object: ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                val adapter = GroupAdapter<ViewHolder>()
                p0.children.forEach{
                    puser.add(PublicUser(it.child("name").getValue(String::class.java)!!,
                        it.child("email").getValue(String::class.java)!!,
                        it.child("phone").getValue(String::class.java)!!,
                        it.child("address").getValue(String::class.java)!!,
                        it.child("date").getValue(String::class.java)!!))
                }
                puser.reverse()
                puser.forEach{
                    adapter.add(it)
                }
                p_user_holder.adapter = adapter
                progressDialog.dismiss()
            }


        })
    }

}