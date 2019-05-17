package com.spanishinquisition.treecompany.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.Project
import kotlinx.android.synthetic.main.project_list_item.view.*

class ProjectsAdapter(
    private val listener: OnProjectSelectedListener
) : RecyclerView.Adapter<ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.project_list_item, parent, false)
        return ProjectViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    interface OnProjectSelectedListener {
        fun OnProjectSelected(project: Project)
    }
}

class ProjectViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val projectImage: ImageView = view.projectImage
    val projectListName: TextView = view.projectListName
    val projectListStatus: TextView = view.projectListStatus
    val projectListPhase: TextView = view.projectListPhase
    val projectListLikes: TextView = view.projectListLikes
    val projectListIdeas: TextView = view.projectListIdeas
    val projectListComments: TextView = view.projectListComments
}