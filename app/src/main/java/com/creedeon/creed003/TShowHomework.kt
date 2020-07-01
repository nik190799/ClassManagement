package com.creedeon.creed003

import android.app.ProgressDialog
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.creedeon.creed003.adapter.THomework
import com.creedeon.creed003.model.Homework
import com.google.firebase.database.*
import java.util.ArrayList


class TShowHomework : AppCompatActivity() {

    private lateinit var reference: DatabaseReference
    internal lateinit var recyclerView: RecyclerView
    internal lateinit var list: ArrayList<Homework>
    internal lateinit var adapter: THomework


    private lateinit var progressDialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tshow_homework)

        progressDialog = ProgressDialog(this@TShowHomework)

        progressDialog.setTitle("Loading...")
        progressDialog.show()

        recyclerView = findViewById<View>(R.id.myRecycler) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        var t_id = intent.getStringExtra("id")

        reference = FirebaseDatabase.getInstance().reference.child(t_id)
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                list = ArrayList()
                for (dataSnapshot1 in dataSnapshot.children) {
                    val p = dataSnapshot1.getValue(Homework::class.java)
                    if (p != null) {
                        list.add(p)
                    }
                }

                list.reverse()

                adapter = THomework(this@TShowHomework, list)

                recyclerView.adapter = adapter

                progressDialog.dismiss()



            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@TShowHomework, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
