package com.creedeon.creed003

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creedeon.creed003.adapter.TeacherCircularAdapter
import com.creedeon.creed003.model.TCircular
import com.google.firebase.database.*

import java.util.ArrayList

class TShowTCircular : AppCompatActivity() {

    internal lateinit var reference: DatabaseReference
    internal lateinit var recyclerView: RecyclerView
    internal lateinit var list: ArrayList<TCircular>
    internal lateinit var adapter: TeacherCircularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tshow_tcircular)

        recyclerView = findViewById<View>(R.id.myRecycler) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)


        reference = FirebaseDatabase.getInstance().reference.child("Teacher Circular")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                list = ArrayList()
                for (dataSnapshot1 in dataSnapshot.children) {

                    val p = dataSnapshot1.getValue(TCircular::class.java)
                    if (p != null) {
                        list.add(p)
                    }
                }

                list.reverse()
                
                adapter = TeacherCircularAdapter(this@TShowTCircular, list)

                recyclerView.adapter = adapter

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@TShowTCircular, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
