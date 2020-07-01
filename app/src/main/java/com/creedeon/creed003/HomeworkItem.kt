package com.creedeon.creed003
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.homework_holder_withoutimg.view.*

class HomeworkItem( var cname:String, var tname:String, var date:String, var postId:String, var sclass:String, var teacher:String):Item<ViewHolder>(){

    override fun getLayout(): Int {
        return R.layout.homework_holder_withoutimg
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.homework_holder_withoutimg.text = cname
        viewHolder.itemView.teacher_homework_name.text = tname
        viewHolder.itemView.date_homeworkhold_withoutimg.text = date
        viewHolder.itemView.postId.text =postId
    }

}