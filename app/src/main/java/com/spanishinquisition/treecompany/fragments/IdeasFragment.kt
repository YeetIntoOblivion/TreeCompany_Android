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
import com.spanishinquisition.treecompany.models.projects.Project
import com.spanishinquisition.treecompany.models.projects.Questionnaire
import com.spanishinquisition.treecompany.rest.getClient
import kotlinx.android.synthetic.main.fragment_ideas.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import retrofit2.Retrofit
import java.lang.Exception


class IdeasFragment : Fragment() {

    private lateinit var listener: IdeaAdapter.IdeaSelectionListener

/*
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is IdeaAdapter.IdeaSelectionListener) {
            listener = context
        } else {
            throw Exception()
        }
    }
*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ideas, container, false)
        val call = getClient().GetIdeas(1)

        call.enqueue(object : Callback<List<Idea>> {
            override fun onResponse(call: Call<List<Idea>>, response: Response<List<Idea>>) {
                val idea = response.body()
                (rvIdeas.adapter as IdeaAdapter).ideas = idea!!.toTypedArray()
            }

            override fun onFailure(call: Call<List<Idea>>, t: Throwable) {
                throw Exception(t)
            }
        })

        view.findViewById<RecyclerView>(R.id.rvIdeas).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = IdeaAdapter(context/*, listener*/)
        }

        return view
    }
}

/*

 fun getProjects() {
         val service = RetroFit.GetClientRf()
         val call = service.GetAllByPlatform(1);

         call.enqueue(object : Callback<List<Project>> {
             override fun onResponse(call: Call<List<Project>>?, response: Response<List<Project>>?) {
                 if (response != null) {
                     list = response.body()
                 }
             }
             override fun onFailure(call: Call<List<Project>>?, t: Throwable?) {
                 try {
                     Log.d("TAG", t!!.message)
                 } catch (e: Exception) {
                 }
             }
         })
     }



}
*/
