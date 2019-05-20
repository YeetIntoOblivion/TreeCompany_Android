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

    var projects: Array<Project> = arrayOf()
    set(projects) {
        field = projects
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.project_list_item, parent, false)
        return ProjectViewHolder(view)
    }

    override fun getItemCount(): Int = projects.size

    override fun onBindViewHolder(vh: ProjectViewHolder, index: Int) {
        val project = projects[index]
        vh.projectImage.visibility = View.INVISIBLE
        vh.projectListName.text = project.title
        vh.projectListStatus.text = project.status
        vh.projectListPhase.text = project.currentPhase.description
        vh.projectListLikes.text = project.likeCount.toString()
        vh.projectListIdeas.visibility = View.INVISIBLE
        vh.projectListComments.text = project.reactionCount.toString()

        vh.itemView.setOnClickListener{
            listener.onProjectSelected(project)
        }
    }

    interface OnProjectSelectedListener {
        fun onProjectSelected(project: Project)
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