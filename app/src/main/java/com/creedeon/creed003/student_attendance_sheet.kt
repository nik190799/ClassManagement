package com.creedeon.creed003

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*

import java.util.ArrayList

class student_attendance_sheet : AppCompatActivity() {
    private var average = 0.0.toFloat()
    private lateinit var t: TextView
    internal var avg: String? = null
    internal lateinit var p1: String
    internal lateinit var p2: String


    var sid:String?= String()
    internal var dates = ArrayList<String>()
    private var ref = FirebaseDatabase.getInstance().reference
    private lateinit var dbAttendance: DatabaseReference
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_attendance_sheet)

        t = findViewById<View>(R.id.textView3) as TextView

        listView = findViewById<View>(R.id.list) as ListView

        sid = intent.getStringExtra("id")


        t.text = sid

        dates.clear()
        dates.add("       Date          " + "p1   "+"  p2")

        dbAttendance = ref.child("attendance")
        dbAttendance.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (dsp in dataSnapshot.children) {
                    p1 = dsp.child(sid!!).child("p1").value!!.toString().substring(0, 1)
                    p2 = dsp.child(sid!!).child("p2").value!!.toString().substring(0, 1)
                    dates.add(dsp.key + "     " + p1+"      "+p2) //add result into array list


                    //  Toast.makeText(getApplicationContext(),dsp.child(student_id).child("p1").getValue().toString(),Toast.LENGTH_LONG).show();
                    if (p1 == "P" || p2 =="P") {

                        P++
                        count++
                    }
                    if (p1 == "A" || p2=="A") {
                        A++
                        count++
                    }


                }
                list(dates, P, count, A)


                //  Toast.makeText(getApplicationContext(), dates.toString(), Toast.LENGTH_LONG).show();

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(applicationContext, "something went wrong", Toast.LENGTH_LONG).show()
            }
        })

    }

    fun list(studentlist: ArrayList<*>, P: Int, count: Int, A: Int) {
        // Toast.makeText(this,NOP+TOC,Toast.LENGTH_LONG).show();
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, studentlist)
        listView.adapter = adapter
        try {

            average = (P * 100 / count).toFloat()
            val avg = java.lang.Float.toString(average)
            t.text = "Attendance"
            if (average >= 75)
                t.setTextColor(Color.GREEN)
            if (average < 75)
                t.setTextColor(Color.RED)
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    companion object {
        var count = 1
        var P = 1
        var A = 1
    }

}
