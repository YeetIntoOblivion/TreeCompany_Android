package com.spanishinquisition.treecompany.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.IdeationQuestionAdapter
import com.spanishinquisition.treecompany.models.projects.IdeationQuestion
import com.spanishinquisition.treecompany.rest.getClient
import kotlinx.android.synthetic.main.fragment_ideation_question.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 *
 */
class IdeationQuestionFragment : Fragment() {

    var moduleId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ideation_question, container, false)

        view.rvIdeationQuestions.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = IdeationQuestionAdapter(context/*, listener*/)
        }
        GetIdeationQuestions(2)
        return view
    }


    fun GetIdeationQuestions(moduleId: Int) {
        val call = getClient().GetIdeationQuestions(moduleId)

        call.enqueue(object : Callback<List<IdeationQuestion>> {
            override fun onResponse(call: Call<List<IdeationQuestion>>, response: Response<List<IdeationQuestion>>) {
                val ideationQuestions = response.body()
                (view!!.rvIdeationQuestions.adapter as IdeationQuestionAdapter).ideationQuestions =
                    ideationQuestions!!.toTypedArray()
            }

            override fun onFailure(call: Call<List<IdeationQuestion>>, t: Throwable) {
                Toast.makeText(
                    this@IdeationQuestionFragment.context,
                    getString(R.string.connection_title),
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }
}
