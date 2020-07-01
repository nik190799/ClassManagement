package com.creedeon.creed003

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_change_password_a.*

class ChangePasswordA : AppCompatActivity() {

    var a_id:String?= String()
    var check:String? = String()

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Admin")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password_a)

        a_id = intent.getStringExtra("id")

        var query: Query = myRef.orderByChild("id").equalTo(a_id.toString())
        query.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach{
                    check = it.child("pass").getValue(String::class.java)


                }
            }
        })


        cp_btn.setOnClickListener {


            if (change_password_edittext != null && check == previous_password_edittext.text.toString()) {
                myRef.child(a_id.toString()).child("pass").setValue(change_password_edittext.text.toString())
                Toast.makeText(this@ChangePasswordA, "Successfully Changed", Toast.LENGTH_SHORT).show()
                change_password_edittext.setText("")
                previous_password_edittext.setText("")
                check=""
            }
            else if(change_password_edittext == null){
                Toast.makeText(this@ChangePasswordA, "Password should not be empty", Toast.LENGTH_SHORT).show()
            }
            else if(check != previous_password_edittext.text.toString()){
                Toast.makeText(this@ChangePasswordA, "Please enter correct previous password", Toast.LENGTH_SHORT).show()

            }
        }

        reset.setOnClickListener {

            myRef.child(a_id.toString()).child("pass").setValue(a_id.toString())
            Toast.makeText(this@ChangePasswordA, "Password reset successfully", Toast.LENGTH_SHORT).show()

        }

    }
}
