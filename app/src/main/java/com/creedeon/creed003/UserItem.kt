package com.creedeon.creed003

import android.util.Log
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.demo.view.*


class UserItem(var name: String, private var roll:String): Item<ViewHolder>() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        Log.d("useritem",name)
        viewHolder.itemView.demotextview.text = name
        viewHolder.itemView.rolltextview.text = roll

        //Picasso.get().load(user.profileImageUrl).into(viewHolder.itemView.imageview_new_message)
    }

    override fun getLayout(): Int {
        return R.layout.demo
    }
}