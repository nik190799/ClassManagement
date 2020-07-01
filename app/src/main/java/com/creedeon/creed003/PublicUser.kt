package com.creedeon.creed003
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.pusers.view.*

class PublicUser(var name:String,var email: String,var phone:String,var address: String,var date:String): Item<ViewHolder>(){


    override fun bind(viewHolder: ViewHolder, position: Int) {
            viewHolder.itemView.pName.text  = name
            viewHolder.itemView.pEmail.text = email
            viewHolder.itemView.pPhn.text   = phone
            viewHolder.itemView.pAdd.text   = address
            viewHolder.itemView.pDate.text  = date

    }

    override fun getLayout(): Int {
        return R.layout.pusers
    }
}