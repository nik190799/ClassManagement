package com.creedeon.creed003

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.homework_holder.view.*

class FileHolderClass( var file_name:String, var file_path:String="", var date:String, var postId:String): Item<ViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.homework_holder
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.homework_holder_recyclerview.text = file_name
        viewHolder.itemView.date_homeworkhold.text = date
        viewHolder.itemView.postId.text = postId
    }

}