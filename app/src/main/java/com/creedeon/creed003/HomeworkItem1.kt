package com.creedeon.creed003

import android.widget.PopupMenu
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_homework_item1.view.*
import kotlinx.android.synthetic.main.activity_homework_item1.view.more
import kotlinx.android.synthetic.main.activity_homework_item1.view.postId


class HomeworkItem1( var cname:String, var tname:String, var date:String, var postId:String):Item<ViewHolder>(){

    val database = FirebaseDatabase.getInstance()
    val myref = database.getReference("Homework")

    override fun getLayout(): Int {
        return R.layout.activity_homework_item1
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.homework_holder_withoutimg.text = cname
        viewHolder.itemView.teacher_homework_name.text = tname
        viewHolder.itemView.date_homeworkhold_withoutimg.text = date
        viewHolder.itemView.postId.text =postId

        viewHolder.itemView.more.setOnClickListener {

            val popupMenu = PopupMenu(it.context,viewHolder.itemView.more)
            popupMenu.menuInflater.inflate(R.menu.option_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.item_delete ->
                    {
                        myref.child(postId).removeValue()
                        //it.context.startActivity(Intent(it.context,TeacherHomeworkEdit::class.java))
                        Toast.makeText(it.context, "Message Deleted", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
            popupMenu.show()
        }


    }

}