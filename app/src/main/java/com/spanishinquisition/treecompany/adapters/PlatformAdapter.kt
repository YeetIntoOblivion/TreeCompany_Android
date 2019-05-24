package com.spanishinquisition.treecompany.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.Platform
import kotlinx.android.synthetic.main.platform_list_item.view.*

/*
 *  @author Edwin Kai-Yin Tam
 */

class PlatformAdapter(private val listener: OnPlatformSelectedListener) : RecyclerView.Adapter<PlatformViewHolder>() {
    var platforms: Array<Platform> = arrayOf()
        set(platforms) {
            field = platforms
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatformViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.platform_list_item, parent, false)
        return PlatformViewHolder(view)
    }

    override fun getItemCount(): Int = platforms.size

    override fun onBindViewHolder(vh: PlatformViewHolder, index: Int) {
        val platform = platforms[index]
        vh.platformName.text = platform.name

        vh.itemView.setOnClickListener {
            listener.onPlatformSelected(platform)
        }
    }

    interface OnPlatformSelectedListener {
        fun onPlatformSelected(platform: Platform)
    }
}

class PlatformViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val platformName: TextView = view.platformName
}