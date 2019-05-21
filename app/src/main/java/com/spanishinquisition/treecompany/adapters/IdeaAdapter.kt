package com.spanishinquisition.treecompany.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.Idea
import com.spanishinquisition.treecompany.models.Module
import com.spanishinquisition.treecompany.models.projects.Project
import kotlinx.android.synthetic.main.idea_list_item.view.*

class IdeaAdapter(
    private val context: Context
/*
    private val ideaSelectionListener: IdeaAdapter.IdeaSelectionListener
*/
) : RecyclerView.Adapter<IdeaAdapter.IdeaViewHolder>() {


    class IdeaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val Titel: TextView = view.IdeaTitel
        val Text: TextView = view.IdeaText
        val Like: TextView = view.IdeaLike
    }

    var ideas: Array<Idea> = arrayOf()
        set(ideas) {
            field = ideas
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(vg: ViewGroup, layoutId: Int): IdeaViewHolder {

        val inflater = LayoutInflater.from(vg.context)
        val view = inflater.inflate(R.layout.idea_list_item, vg, false)
        return IdeaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ideas.size
    }


    override fun onBindViewHolder(vh: IdeaViewHolder, index: Int) {
        val idea = ideas[index]

        vh.Titel.text = idea.title
        vh.Text.text = idea.field!!.text
        vh.Like.text = idea.voteCount.toString()

       /* vh.itemView.setOnClickListener {
            ideaSelectionListener.onIdeaSelected(ideas[index])
        }*/
    }

    interface IdeaSelectionListener {
        fun onIdeaSelected(idea: Idea)
    }


}
