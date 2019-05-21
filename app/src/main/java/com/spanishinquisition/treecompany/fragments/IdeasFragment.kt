package com.spanishinquisition.treecompany.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.IdeaAdapter
import com.spanishinquisition.treecompany.models.projects.Project
import com.spanishinquisition.treecompany.rest.RetroFit
 import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class IdeasFragment : Fragment() {
    internal var list: List<Project>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ideas, container, false)

        getProjects()

        view.findViewById<RecyclerView>(R.id.rvIdeas).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = IdeaAdapter(this.context, list)
        }



        return view;
    }


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
