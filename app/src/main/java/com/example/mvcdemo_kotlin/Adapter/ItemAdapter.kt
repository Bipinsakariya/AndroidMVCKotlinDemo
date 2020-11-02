package com.example.mvcdemo_kotlin.Adapter

import android.content.ClipboardManager
import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mvcdemo_kotlin.R
import com.example.mvcdemo_kotlin.model.Item
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class ItemAdapter(
    val mContext: Context,
    val list: ArrayList<Item>
) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {


         var txt_itemtitle: TextView? = null
         var txt_ownername: TextView? = null
         var iv_owner: ImageView? = null

        init {
            iv_owner = itemview.findViewById<ImageView>(R.id.iv_owner)
            txt_itemtitle = itemview.findViewById<TextView>(R.id.txt_itemtitle)
            txt_ownername = itemview.findViewById<TextView>(R.id.txt_ownername)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(mContext).inflate(R.layout.row_of_items, parent, false)
        return ViewHolder(v)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.txt_ownername!!.text = list[position].owner.displayName
        holder.txt_itemtitle!!.text = list[position].title

        Picasso.with(mContext).load(list[position].owner.profileImage).into(holder.iv_owner)



    }

    override fun getItemCount(): Int {
        return list.size
    }


}