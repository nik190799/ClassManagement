package com.creedeon.creed003


import android.content.Intent
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_teacher_admin_circular_item1.view.*
import kotlinx.android.synthetic.main.teacher_admin_circular_item.view.date_circular_holder_withoutimg
import kotlinx.android.synthetic.main.teacher_admin_circular_item.view.teacher_circular_holder_withoutimg


class TeacherAdminCircularItem1( var cname:String, var date:String, var postId:String): Item<ViewHolder>(){

    val database = FirebaseDatabase.getInstance()
    val myref = database.getReference("Teacher Circular")

    override fun getLayout(): Int {
        return R.layout.activity_teacher_admin_circular_item1
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.teacher_circular_holder_withoutimg.text = cname
        viewHolder.itemView.date_circular_holder_withoutimg.text = date
        viewHolder.itemView.id_teachermsg.text = postId

        viewHolder.itemView.more.setOnClickListener {

            val popupMenu = PopupMenu(it.context,viewHolder.itemView.more)
            popupMenu.menuInflater.inflate(R.menu.option_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.item_delete ->
                    {
                        myref.child(postId).removeValue()
                        notifyChanged()
                        //it.context.startActivity(Intent(it.context,UploaderActivity::class.java))
                        Toast.makeText(it.context, "Message Deleted", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
            popupMenu.show()
        }

    }

}