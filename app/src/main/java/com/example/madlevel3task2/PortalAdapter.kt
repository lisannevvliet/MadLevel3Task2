package com.example.madlevel3task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel3task2.databinding.ItemPortalBinding

class PortalAdapter(private val portals: List<Portal>, private val clickListener: (Portal) -> Unit) : RecyclerView.Adapter<PortalAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemPortalBinding.bind(itemView)

        // Specify which data in Portal.kt corresponds to which TextViews.
        fun databind(portal: Portal, clickListener: (Portal) -> Unit) {
            binding.tvTitle.text = portal.title
            binding.tvUrl.text = portal.url
            // Set an OnClickListener on the portals, to be able to open the URL's later.
            itemView.setOnClickListener { clickListener(portal) }
        }
    }

    // Create and return a ViewHolder object, inflate a standard layout called simple_list_item_1.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    // Return the size of the list.
    override fun getItemCount(): Int {
        return portals.size
    }

    // Display the data at the specified position, called by RecyclerView.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(portals[position], clickListener)
    }
}