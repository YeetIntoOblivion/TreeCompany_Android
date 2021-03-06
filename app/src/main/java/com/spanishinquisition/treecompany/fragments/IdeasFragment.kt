package com.spanishinquisition.treecompany.fragments


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.IdeaAdapter
import com.spanishinquisition.treecompany.models.Idea
import com.spanishinquisition.treecompany.rest.getClient
import kotlinx.android.synthetic.main.fragment_ideas.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

/*
 *  @author David Matei
 */

class IdeasFragment : Fragment() {

    // private lateinit var listener: IdeaAdapter.IdeaSelectionListener

    var iQuestionId: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ideas, container, false)
        view.findViewById<RecyclerView>(R.id.rvIdeas).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = IdeaAdapter(/*context, listener*/)
        }
        getIdeas(1)
        return view
    }


    fun getIdeas(iQuestionId: Int) {
        val call = getClient().getIdeas(iQuestionId)

        call.enqueue(object : Callback<List<Idea>> {
            override fun onResponse(call: Call<List<Idea>>, response: Response<List<Idea>>) {
                val idea = response.body()
                (rvIdeas.adapter as IdeaAdapter).ideas = idea!!.toTypedArray()
            }

            override fun onFailure(call: Call<List<Idea>>, t: Throwable) {
                Toast.makeText(
                    this@IdeasFragment.context,
                    getString(R.string.dialog_connection_title),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}

