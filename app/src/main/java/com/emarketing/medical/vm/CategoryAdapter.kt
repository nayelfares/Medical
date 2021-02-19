package com.emarketing.medical.vm

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emarketing.medical.R
import com.emarketing.medical.api.toUrl
import com.emarketing.medical.data.Category
import com.emarketing.medical.ui.Search
import kotlinx.android.synthetic.main.category_item_row.view.*
import kotlinx.android.synthetic.main.search_item_row.view.name


class CategoryAdapter(val context:Context, val categories:ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.category_item_row, parent, false))
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categories[position]
        holder.name.text=category.name
        Glide.with(context)
            .load(category.image.toUrl())
            .into(holder.icon)
        if (position%2==0)
            holder.row.setBackgroundColor(context.getColor(R.color.colorAccentTransparence))
        else
            holder.row.setBackgroundColor(context.getColor(R.color.white))
        holder.row.setOnClickListener {
            val intent=Intent(context,Search::class.java)
            intent.putExtra("categoryId", category.id)
            intent.putExtra("fromCategory", true)
            context.startActivity(intent)
        }
    }

    // total number of rows
    override fun getItemCount(): Int {
        return categories.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var icon=itemView.icon
        val name=itemView.name
        val row =itemView.row

    }
}