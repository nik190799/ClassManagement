package com.creedeon.creed003

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creedeon.creed003.notepad.NoteActivity
import kotlinx.android.synthetic.main.fragment_teacher_tab3.*
import java.text.SimpleDateFormat
import java.util.*


class TeacherTab3 : androidx.fragment.app.Fragment() {

    internal var date = SimpleDateFormat("dd/MM/yyyy").format(Date())
    var t_id:String = String()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teacher_tab3, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        t_id = arguments?.getString("id").toString()



        t_tab3.text = date

        teacher_homework_edit_btn.setOnClickListener {
            var intent = Intent(it.context,TeacherHomeworkEdit::class.java)
            intent.putExtra("id",t_id)
            startActivity(intent)
        }

        show_homework.setOnClickListener{
            val intent = Intent(context,TShowHomework::class.java)
            intent.putExtra("id",t_id)
            startActivity(intent)
        }
        notepad_btn.setOnClickListener {
            startActivity(Intent(context,NoteActivity::class.java))
        }


    }
}