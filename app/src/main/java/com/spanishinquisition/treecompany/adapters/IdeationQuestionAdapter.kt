package com.spanishinquisition.treecompany.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.projects.IdeationQuestion
import kotlinx.android.synthetic.main.ideationquestion_list_item.view.*


class IdeationQuestionAdapter(
    context: Context,
    private val listener: OnIdeationQuestionSelectionListener
) :
    RecyclerView.Adapter<IdeationQuestionAdapter.IdeationQuestionViewHolder>() {

    class IdeationQuestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val Titel: TextView = view.tvQtitle
        var Description: TextView = view.tvIDescription
        var SiteURL: TextView = view.tvSiteUrl


    }

    var ideationQuestions: Array<IdeationQuestion> = arrayOf()
        set(ideationQuestions) {
            field = ideationQuestions
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(vg: ViewGroup, layoutId: Int): IdeationQuestionViewHolder {

        val inflater = LayoutInflater.from(vg.context)
        val view = inflater.inflate(R.layout.ideationquestion_list_item, vg, false)
        return IdeationQuestionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ideationQuestions.size
    }


    override fun onBindViewHolder(vh: IdeationQuestionViewHolder, index: Int) {
        val ideationQuestions = ideationQuestions[index]
        vh.Titel.text = ideationQuestions.questionTitle
        vh.Description.text = ideationQuestions.description
        vh.SiteURL.text = ideationQuestions.siteUrl

        vh.itemView.setOnClickListener {
            listener.onIdeationQuestionSelected(ideationQuestions.id)
        }
    }

    interface OnIdeationQuestionSelectionListener {
        fun onIdeationQuestionSelected(iQuestionId: Int)
    }

}
