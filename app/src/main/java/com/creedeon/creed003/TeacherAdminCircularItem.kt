package com.creedeon.creed003

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.teacher_admin_circular_item.view.*

class TeacherAdminCircularItem( var cname:String, var date:String, var postId:String): Item<ViewHolder>(){

    override fun getLayout(): Int {
        return R.layout.teacher_admin_circular_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.teacher_circular_holder_withoutimg.text = cname
        viewHolder.itemView.date_circular_holder_withoutimg.text = date
        viewHolder.itemView.id_teachermsg.text=postId
    }

}