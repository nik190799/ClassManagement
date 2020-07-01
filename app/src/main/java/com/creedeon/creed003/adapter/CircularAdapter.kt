package com.creedeon.creed003.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.creedeon.creed003.R
import com.creedeon.creed003.model.Circular
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList



class CircularAdapter(internal var context: Context, internal var homework: ArrayList<Circular>) :
    RecyclerView.Adapter<CircularAdapter.MyViewHolder>() {
    private var mdatabase = FirebaseDatabase.getInstance().reference.child("Circular")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.circular_adapter, parent, false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.file_name.text = homework[position].file_name
        holder.date.text = homework[position].date
        holder.more.setOnClickListener {

            val popupMenu = PopupMenu(it.context,holder.more)
            popupMenu.menuInflater.inflate(R.menu.admin_circular_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.item_delete ->
                    {
                        mdatabase.child(homework[position].postId).removeValue()
                        notifyItemRemoved(holder.adapterPosition)
                        Toast.makeText(it.context, "Message Deleted", Toast.LENGTH_SHORT).show()
                    }
                    R.id.item_download ->{

                        if (homework[position].file_path!="") {
                            var uri: Uri = Uri.parse(homework[position].file_path)
                            var intent = Intent(Intent.ACTION_VIEW, uri)
                                context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "File is not attached...", Toast.LENGTH_SHORT).show()
                        }

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
        var file_name: TextView = itemView.findViewById<View>(R.id.homework_holder_recyclerview) as TextView
        var date: TextView = itemView.findViewById(R.id.date_homeworkhold)
        var more: ImageView = itemView.findViewById(R.id.more)
    }
}