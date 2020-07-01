package com.creedeon.creed003

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.teacher_homework_edit.*
import java.text.SimpleDateFormat
import java.util.*

class TeacherHomeworkEdit:AppCompatActivity(), AdapterView.OnItemSelectedListener {


    val database = FirebaseDatabase.getInstance()
    val myRef= database.getReference("Homework")

    internal lateinit var item: String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teacher_homework_edit)


        val spinner = findViewById<View>(R.id.spinner_homework) as Spinner


        spinner.onItemSelectedListener = this
        val categories = ArrayList<String>()
        //categories.add("S11M")
        //categories.add("S11B")
        categories.add("S12M")
        categories.add("S12B")


        val dataAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = dataAdapter


        var teacher = intent.getStringExtra("id")
        val myref  = database.getReference(teacher)


        add_homework.setOnClickListener {
            if(homework_edittext.text.toString()==""){
                homework_edittext.error = "Enter the Homework"
            }
            if(teacher_name_text.text.toString()==""){
                homework_edittext.error = "Enter the Teacher name"
            }
            else{
                var postId = myRef.child(item).push().key
                var sclass = item
                var date = SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()).format(Date())
                var homework = HomeworkItem(homework_edittext.text.toString(),
                                            teacher_name_text.text.toString(),
                                                                        date,
                                                            postId.toString(),
                                                                      sclass,
                                                                     teacher)

                myRef.child(item).child(postId.toString()).setValue(homework)
                myref.child(postId.toString()).setValue(homework)
                homework_edittext.setText("")
                teacher_name_text.setText("")
                Toast.makeText(this@TeacherHomeworkEdit,"Homework added successfully", Toast.LENGTH_SHORT).show()
            }

        }



    }


    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            item = parent.getItemAtPosition(position).toString()
        }
    }


}