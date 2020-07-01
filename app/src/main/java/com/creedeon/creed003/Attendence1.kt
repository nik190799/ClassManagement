package com.creedeon.creed003

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_attendence1.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Attendance1 : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    internal lateinit var list: ArrayList<String>
    private lateinit var reference: DatabaseReference


    internal lateinit var item: String

    var ref = FirebaseDatabase.getInstance().reference
    var dbStudent = ref.child("Student")
    var dbAttendance = ref.child("attendance")
    lateinit var sid: String

    var myRef = FirebaseDatabase.getInstance().reference.child("Users")

    private lateinit var progressDialog: ProgressDialog



    internal var date = SimpleDateFormat("dd-MM-yyyy").format(Date())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendence1)
        val spinner2 = findViewById<View>(R.id.spinner2) as Spinner





        spinner2.onItemSelectedListener = this
        val categories = ArrayList<String>()
        //categories.add("S11M")
        //categories.add("S11B")
        categories.add("S12M")
        categories.add("S12B")

        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner2.adapter = dataAdapter


        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Loading...")
        progressDialog.setMessage("please wait!")
        progressDialog.show()

        reference = FirebaseDatabase.getInstance().reference.child("attendance")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                var temp=0
                for (dataSnapshot1 in dataSnapshot.children) {

                    var key = dataSnapshot1.key
                    Log.d("key_date", "key :$key")

                    if(key==date){
                        temp = 1
                    }
                }

                if(temp==1){
                    create_database_btn.visibility = View.GONE
                }
                progressDialog.dismiss()
                //Toast.makeText(this@Attendance1,temp.toString() , Toast.LENGTH_SHORT).show()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                progressDialog.dismiss()
                Toast.makeText(this@Attendance1, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show()
            }
        })




    }




    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        item = parent.getItemAtPosition(position).toString()
        //  Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {
        // TODO Auto-generated method stub
    }

    fun takeAttendanceButton(v: View) {

        if (create_database_btn.visibility == View.VISIBLE) {
            Toast.makeText(this, "Create Database!!", Toast.LENGTH_LONG).show()
        } else {
            val basket = Bundle()
            basket.putString("class_selected", item)

            val intent = Intent(this, takeAttendance::class.java)
            intent.putExtras(basket)
            startActivity(intent)
            this.finish()
        }

    }

    fun previous_records(v: View) {

        val basket = Bundle()
        basket.putString("class_selected", item)

        val intent = Intent(this, teacher_attendanceSheet::class.java)
        intent.putExtras(basket)
        startActivity(intent)

    }

    fun CreateAttendance(v: View) {

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Creating Database...")
        progressDialog.setMessage("please wait!")
        progressDialog.show()



        dbStudent.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                val P1 = "-"
                val P2 = "-"

                val a = Attendance_sheet(P1, P2)

                for (dsp in dataSnapshot.children) {
                    sid = dsp.child("sid").value!!.toString()
                    dbAttendance.child(date).child(sid).setValue(a)

                }
                progressDialog.dismiss()

                Toast.makeText(applicationContext, "successfully created $date db", Toast.LENGTH_LONG).show()
                create_database_btn.visibility = View.GONE

            }

            override fun onCancelled(databaseError: DatabaseError) {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "something wrong", Toast.LENGTH_LONG).show()
            }

        })



    }

    companion object {
        private val back_pressed: Long = 0

    }
}
