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
import com.emarketing.medical.data.Site
import com.emarketing.medical.ui.LocationDetails
import kotlinx.android.synthetic.main.search_item_row.view.*


class ResultAdapter(val context:Context, val results:ArrayList<Site>) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.search_item_row, parent, false))
        return view
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]
        holder.city.text=result.city_name
        Glide.with(context)
            .load(result.profile_photo.toUrl())
            .into(holder.image)
        holder.description.text=result.description
        holder.name.text=result.name

        holder.image.setOnClickListener {
            val intent=Intent(context,LocationDetails::class.java)
            intent.putExtra("location", result)
            context.startActivity(intent)
//            val link="https://maps.google.com/?q=${result.latitude},${result.longitude}"
//            try {
//
//                val browserIntent = Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse(link)
//                )
//                context.startActivity(browserIntent)
//            } catch (e: Exception) {
//            }
        }
    }

    // total number of rows
    override fun getItemCount(): Int {
        return results.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        var image=itemView.image
        val name=itemView.name
        val description =itemView.description
        val city =itemView.city

    }
}