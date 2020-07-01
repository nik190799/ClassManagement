package com.creedeon.creed003

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creedeon.creed003.adapter.CircularAdapter
import com.creedeon.creed003.model.Circular
import com.google.firebase.database.*

class AdminCircular : AppCompatActivity() {


    private lateinit var reference: DatabaseReference
    internal lateinit var recyclerView: RecyclerView
    internal lateinit var list: ArrayList<Circular>
    internal lateinit var adapter: CircularAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_circular)

        recyclerView = findViewById<View>(R.id.myRecycler) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)


        reference = FirebaseDatabase.getInstance().reference.child("Circular")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                list = ArrayList()
                for (dataSnapshot1 in dataSnapshot.children) {
                    val p = dataSnapshot1.getValue(Circular::class.java)
                    if (p != null) {
                        list.add(p)
                    }
                }

                list.reverse()

                adapter = CircularAdapter(this@AdminCircular, list)



                recyclerView.adapter = adapter

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@AdminCircular, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
