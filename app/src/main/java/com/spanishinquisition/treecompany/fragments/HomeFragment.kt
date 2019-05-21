package com.spanishinquisition.treecompany.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.ProjectsAdapter
import com.spanishinquisition.treecompany.models.Project
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), ProjectsAdapter.OnProjectSelectedListener{
    private lateinit var projectListFilterSP: Spinner
    private lateinit var projectListRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        initialiseViews(view)
        return view
    }

    private fun initialiseViews(view: View) {
        projectListFilterSP = view.projectListFilterSP
        projectListRV = view.projectListRV

        projectListRV.apply {
            layoutManager = LinearLayoutManager(context)
//            adapter = ProjectsAdapter(context)
        }
    }

    override fun OnProjectSelected(project: Project) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
