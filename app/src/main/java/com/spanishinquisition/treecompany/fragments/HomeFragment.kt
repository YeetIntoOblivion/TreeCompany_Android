package com.spanishinquisition.treecompany.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
//import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.ProjectAdapter
import com.spanishinquisition.treecompany.models.projects.Project
import com.spanishinquisition.treecompany.rest.getClient
import kotlinx.android.synthetic.main.fragment_home.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.channels.Selector

/*
 *  @author Edwin Kai-Yin Tam
 */

class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var listener: ProjectAdapter.OnProjectSelectedListener
    private lateinit var spinner: Spinner


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ProjectAdapter.OnProjectSelectedListener)
            listener = context
        else
            throw RuntimeException("Parent context of HomeFragment is incorrect")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.projectListRV
            .apply {
                adapter = ProjectAdapter(listener)
                layoutManager = LinearLayoutManager(context)
            }

        initialiseViews(view)

        return view
    }

    private fun initialiseViews(view: View) {
        spinner = view.projectListFilterSP
        ArrayAdapter.createFromResource(
            this@HomeFragment.context!!,
            R.array.project_list_filter_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (position) {
            0 -> {
                getSortedProjects(0)
            }
            1 -> {
                getSortedProjects(1)
            }
            2 -> {
                getSortedProjects(2)
            }
            3 -> {
                getSortedProjects(3)
            }
            4 -> {
                getSortedProjects(4)
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    private fun getSortedProjects(quota: Int) {
        val platformId = this.activity!!.getSharedPreferences(getString(R.string.app_pref),Context.MODE_PRIVATE).getInt(getString(R.string.pref_platform_id), resources.getInteger(R.integer.default_platform_id))
        val call = getClient().sortedBy(quota, platformId)
        call.enqueue(object : Callback<List<Project>> {
            override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>) {
                val projects = response.body()
                (view!!.projectListRV.adapter as ProjectAdapter).projects = projects!!.toTypedArray()
            }

            override fun onFailure(call: Call<List<Project>>, t: Throwable) {
                Toast.makeText(
                    this@HomeFragment.context,
                    getString(R.string.dialog_connection_title),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}