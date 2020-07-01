package com.creedeon.creed003.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.creedeon.creed003.R
import com.creedeon.creed003.model.TCircular
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList



class TeacherCircularAdapter(internal var context: Context, internal var homework: ArrayList<TCircular>) :
    RecyclerView.Adapter<TeacherCircularAdapter.MyViewHolder>() {
    private var mdatabase = FirebaseDatabase.getInstance().reference.child("Teacher Circular")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.t_circular_adapter, parent, false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.cname.text = homework[position].cname
        holder.date.text = homework[position].date
        holder.more.setOnClickListener {

            val popupMenu = PopupMenu(it.context,holder.more)
            popupMenu.menuInflater.inflate(R.menu.option_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.item_delete ->
                    {
                        mdatabase.child(homework[position].postId).removeValue()
                        notifyItemRemoved(holder.adapterPosition)
                        Toast.makeText(it.context, "Message Deleted", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
            popupMenu.show()
        }

    }

    override fun getItemCount(): Int {
        return homework.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cname: TextView = itemView.findViewById<View>(R.id.teacher_circular_holder_withoutimg) as TextView
        var date: TextView = itemView.findViewById(R.id.date_circular_holder_withoutimg)
        var more: ImageView = itemView.findViewById(R.id.more)

    }
}