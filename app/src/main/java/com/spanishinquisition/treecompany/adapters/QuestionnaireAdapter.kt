package com.spanishinquisition.treecompany.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.projects.Module
import com.spanishinquisition.treecompany.models.projects.Questionnaire
import kotlinx.android.synthetic.main.questionnaire_list_item.view.*

class QuestionnaireAdapter(/*context: Context*/):
    RecyclerView.Adapter<QuestionnaireAdapter.QuestionnaireViewHolder>() {

    class QuestionnaireViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val Titel: TextView = view.tvTitel
        val LikeCount: TextView = view.tvLikeCount
        val FbCount: TextView = view.tvFbCount
        val TwitterCount: TextView = view.tvTwitterCount
    }

    var questionnaires: Array<Questionnaire> = arrayOf()
        set(questionnaires) {
            field = questionnaires
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(vg: ViewGroup, layoutId: Int): QuestionnaireViewHolder {

        val inflater = LayoutInflater.from(vg.context)
        val view = inflater.inflate(R.layout.questionnaire_list_item, vg, false)
        return QuestionnaireViewHolder(view)
    }

    override fun getItemCount(): Int {
        return questionnaires.size
    }


    override fun onBindViewHolder(vh: QuestionnaireViewHolder, index: Int) {
        val questionnaire = questionnaires[index]

        vh.Titel.text = questionnaire.title
        vh.LikeCount.text = questionnaire.likeCount.toString()
        vh.FbCount.text = questionnaire.fbLikeCount.toString()
        vh.TwitterCount.text = questionnaire.twitterLikeCount.toString()

        /* vh.itemView.setOnClickListener {
             moduleSelectionListener.onModuleSelected(questionnaires[index])
         }*/
    }

    interface QuestionnaireSelectionListener {
        fun onQuestionnaireSelected(module: Module)
    }

}
