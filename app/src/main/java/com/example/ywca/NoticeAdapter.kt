package com.example.ywca

import android.net.Uri
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.ywca.network.Property


class NoticeAdapter(private val data: List<Property>) : RecyclerView.Adapter<NoticeAdapter.MyViewHolder>()  {


    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        private val cardView = view.findViewById<CardView>(R.id.cv_notice_item)
        fun bind(property: Property){
            val title = view.findViewById<TextView>(R.id.tvTitle)
            val description = view.findViewById<TextView>(R.id.tvDescription)
            val preWord = "Date :"

            title.text = property.notice_subject
            description.text = preWord + property.notice_date

            cardView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(property.notice))
                view.context.startActivity(intent)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.notice_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])

    }

}