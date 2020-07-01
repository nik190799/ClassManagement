package com.creedeon.creed003


import android.content.Intent
import android.widget.PopupMenu
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_file_holder_class1.view.*
import kotlinx.android.synthetic.main.activity_file_holder_class1.view.more


class FileHolderClass1( var file_name:String, var file_path:String="", var date:String, var postId:String): Item<ViewHolder>() {


    val database = FirebaseDatabase.getInstance()
    val myref = database.getReference("Circular")
    override fun getLayout(): Int {
        return R.layout.activity_file_holder_class1
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.homework_holder_recyclerview.text = file_name
        viewHolder.itemView.date_homeworkhold.text = date
        viewHolder.itemView.postId.text = postId
        viewHolder.itemView.more.setOnClickListener {

            val popupMenu = PopupMenu(it.context,viewHolder.itemView.more)
            popupMenu.menuInflater.inflate(R.menu.option_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.item_delete ->
                    {
                        myref.child(postId).removeValue()
                        it.context.startActivity(Intent(it.context,UploaderActivity::class.java))
                        Toast.makeText(it.context, "Message Deleted", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
            popupMenu.show()
        }
    }

}