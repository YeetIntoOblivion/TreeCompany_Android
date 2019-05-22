package com.spanishinquisition.treecompany.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.projects.Module
import kotlinx.android.synthetic.main.module_list_item.view.*

class ModuleAdapter(
    private val context: Context
/*
    private val moduleSelectionListener: ModuleAdapter.ModuleSelectionListener
*/
) : RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder>() {
    class ModuleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val Titel: TextView = view.tvTitel
        val LikeCount: TextView = view.tvLikeCount
        val FbCount: TextView = view.tvFbCount
        val TwitterCount: TextView = view.tvTwitterCount
    }

    var modules: Array<Module> = arrayOf()
        set(modules) {
            field = modules
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(vg: ViewGroup, layoutId: Int): ModuleViewHolder {

        val inflater = LayoutInflater.from(vg.context)
        val view = inflater.inflate(R.layout.module_list_item, vg, false)
        return ModuleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return modules.size
    }


    override fun onBindViewHolder(vh: ModuleViewHolder, index: Int) {
        val module = modules[index]

        vh.Titel.text = module.title
        vh.LikeCount.text = module.likeCount.toString()
        vh.FbCount.text = module.fbLikeCount.toString()
        vh.TwitterCount.text = module.twitterLikeCount.toString()

        /* vh.itemView.setOnClickListener {
             moduleSelectionListener.onModuleSelected(modules[index])
         }*/
    }

    interface ModuleSelectionListener {
        fun onModuleSelected(module: Module)
    }

}
