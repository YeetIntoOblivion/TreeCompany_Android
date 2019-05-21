package com.spanishinquisition.treecompany.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.Idea
import com.spanishinquisition.treecompany.models.projects.Project
import kotlinx.android.synthetic.main.idea_list_item.view.*

class IdeaAdapter(
    private val context: Context,
    private val list: List<Project>?

) : RecyclerView.Adapter<IdeaViewHolder>() {


    override fun onCreateViewHolder(vg: ViewGroup, layoutId: Int): IdeaViewHolder {

        val inflater = LayoutInflater.from(vg.context)
        val view = inflater.inflate(R.layout.idea_list_item, vg, false)
        return IdeaViewHolder(view)
    }

    override fun onBindViewHolder(vh: IdeaViewHolder, index: Int) {
        val idea = list?.get(index)
        if (idea != null) {
            vh.Titel.text = idea.goal
            vh.Text.text = idea.status
            vh.Like.text = idea.title
        }

    }


    override fun getItemCount(): Int {

        if (list != null) {
            return list.size
        }
        return 0

    }

}

class IdeaViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val Titel: TextView = view.IdeaTitel
    val Text: TextView = view.IdeaText
    val Like: TextView = view.IdeaLike

}