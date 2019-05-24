package com.spanishinquisition.treecompany.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.QuestionnaireAdapter
import com.spanishinquisition.treecompany.models.projects.Questionnaire
import com.spanishinquisition.treecompany.rest.getClient
import kotlinx.android.synthetic.main.fragment_questionnaire.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 *
 */
class QuestionnaireFragment : Fragment() {

    var projectId: Int = 0;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_questionnaire, container, false)

        view.rvQuestionnaire.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = QuestionnaireAdapter(/*context*//*, listener*/)
        }


        GetQuestionnaires(projectId)



        return view
    }


    fun GetQuestionnaires(projectId: Int) {
        val call = getClient().GetQuestionnaires(projectId)

        call.enqueue(object : Callback<List<Questionnaire>> {
            override fun onResponse(call: Call<List<Questionnaire>>, response: Response<List<Questionnaire>>) {
                val questionnaires = response.body()
                (view!!.rvQuestionnaire.adapter as QuestionnaireAdapter).questionnaires =
                    questionnaires!!.toTypedArray()
            }

            override fun onFailure(call: Call<List<Questionnaire>>, t: Throwable) {
                Toast.makeText(
                    this@QuestionnaireFragment.context,
                    getString(R.string.connection_title),
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }


}
