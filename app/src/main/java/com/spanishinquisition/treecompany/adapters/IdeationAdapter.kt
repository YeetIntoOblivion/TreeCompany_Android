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
import kotlinx.android.synthetic.main.ideation_list_item.view.*

class IdeationAdapter(
    context: Context
/*
    private val listener: OnIdeationSelectionListener
*/
) :
    RecyclerView.Adapter<IdeationAdapter.IdeationViewHolder>() {

    class IdeationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val Titel: TextView = view.tvTitel
        val ExtraInfo: TextView = view.tvExtraInfo
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
        vh.ExtraInfo.text = ideations.extraInfo
        vh.LikeCount.text = ideations.likeCount.toString()
        vh.FbCount.text = ideations.fbLikeCount.toString()
        vh.TwitterCount.text = ideations.twitterLikeCount.toString()

  /*      vh.itemView.setOnClickListener {
            listener.onIdeationSelected(ideations.id)
        }*/
    }

   /* interface OnIdeationSelectionListener {
        fun onIdeationSelected(ideationId: Int)
    }*/
}
