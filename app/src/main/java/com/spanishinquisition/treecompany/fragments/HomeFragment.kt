package com.spanishinquisition.treecompany.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.ProjectsAdapter
import com.spanishinquisition.treecompany.models.Project
import com.spanishinquisition.treecompany.rest.RestClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    private lateinit var listener: ProjectsAdapter.OnProjectSelectedListener

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
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
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
                    }, {
                        Toast.makeText(this@HomeFragment.context, it.message, Toast.LENGTH_LONG).show()
                    })
            }
        return view
    }
}