package com.creedeon.creed003



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.change_password.*


class ChangePassword : AppCompatActivity(){
    var s_id:String?= String()
    var check:String? = String()


    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("Student")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password)


        s_id = intent.getStringExtra("id")

        var query: Query = myRef.orderByChild("sid").equalTo(s_id.toString())
        query.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach{
                    check = it.child("spass").getValue(String::class.java)


                }
            }
        })


        cp_btn.setOnClickListener {


            if (change_password_edittext != null && check == previous_password_edittext.text.toString()) {
                myRef.child(s_id.toString()).child("spass").setValue(change_password_edittext.text.toString())
                Toast.makeText(this@ChangePassword, "Successfully Changed", Toast.LENGTH_SHORT).show()
                change_password_edittext.setText("")
                previous_password_edittext.setText("")
                check=""
            }
            else if(change_password_edittext == null){
                Toast.makeText(this@ChangePassword, "Password should not be empty", Toast.LENGTH_SHORT).show()
            }
            else if(check != previous_password_edittext.text.toString()){
                Toast.makeText(this@ChangePassword, "Please enter correct previous password", Toast.LENGTH_SHORT).show()

            }
        }

        reset.setOnClickListener {

            myRef.child(s_id.toString()).child("spass").setValue(s_id.toString())
            Toast.makeText(this@ChangePassword, "Password reset successfully", Toast.LENGTH_SHORT).show()

        }

    }

}