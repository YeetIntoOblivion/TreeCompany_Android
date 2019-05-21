package com.spanishinquisition.treecompany.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.ProjectsAdapter
import com.spanishinquisition.treecompany.models.Project
import com.spanishinquisition.treecompany.rest.RestClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var listener: ProjectsAdapter.OnProjectSelectedListener
    private lateinit var spinner: Spinner
    private lateinit var projects: Array<Project>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ProjectsAdapter.OnProjectSelectedListener)
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
                adapter = ProjectsAdapter(listener)
                layoutManager = LinearLayoutManager(context)

                RestClient(context)
                    .getProjects(1)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({
                        (adapter as ProjectsAdapter).projects = it
                        projects = it
                    }, {
                        Toast.makeText(
                            this@HomeFragment.context,
                            getString(R.string.connection_title),
                            Toast.LENGTH_LONG
                        ).show()
                    })
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
                projects.sortBy { project -> project.title }

                view?.projectListRV?.adapter?.notifyDataSetChanged()
            }
            1 -> {
                projects.sortBy { project -> project.status }
                view?.projectListRV?.adapter?.notifyDataSetChanged()
            }
            2 -> {
                projects.sortBy { project -> project.likeCount }
                view?.projectListRV?.adapter?.notifyDataSetChanged()
            }
            3 -> {
                projects.sortBy { project -> project.reactionCount }
                view?.projectListRV?.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}