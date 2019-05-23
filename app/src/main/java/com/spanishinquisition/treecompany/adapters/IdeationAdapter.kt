package com.spanishinquisition.treecompany.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.projects.Ideation
import com.spanishinquisition.treecompany.models.projects.Module
 import kotlinx.android.synthetic.main.module_list_item.view.*

class IdeationAdapter(context: Context) :
    RecyclerView.Adapter<IdeationAdapter.IdeationViewHolder>() {

    class IdeationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val Titel: TextView = view.tvTitel
        val LikeCount: TextView = view.tvLikeCount
        val FbCount: TextView = view.tvFbCount
        val TwitterCount: TextView = view.tvTwitterCount
    }

    var ideations: Array<Ideation> = arrayOf()
        set(ideations) {
            field = ideations
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(vg: ViewGroup, layoutId: Int): IdeationViewHolder {

        val inflater = LayoutInflater.from(vg.context)
        val view = inflater.inflate(R.layout.ideation_list_item, vg, false)
        return IdeationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ideations.size
    }


    override fun onBindViewHolder(vh: IdeationViewHolder, index: Int) {
        val ideations = ideations[index]
        vh.Titel.text = ideations.title
        vh.LikeCount.text = ideations.likeCount.toString()
        vh.FbCount.text = ideations.fbLikeCount.toString()
        vh.TwitterCount.text = ideations.twitterLikeCount.toString()

        /* vh.itemView.setOnClickListener {
             moduleSelectionListener.onModuleSelected(ideations[index])
         }*/
    }

    interface IdeationSelectionListener {
        fun onIdeationSelected(module: Module)
    }

}
