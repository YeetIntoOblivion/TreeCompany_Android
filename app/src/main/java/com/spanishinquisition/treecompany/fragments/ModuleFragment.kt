package com.spanishinquisition.treecompany.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.ModuleAdapter
import com.spanishinquisition.treecompany.models.projects.Module
import com.spanishinquisition.treecompany.rest.getClient
import kotlinx.android.synthetic.main.fragment_module.*
import kotlinx.android.synthetic.main.fragment_module.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.nio.channels.Selector

class ModuleFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_module, container, false)

        view.rvModule.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ModuleAdapter(context/*, listener*/)
        }
        GetModules(1)

        return view;
    }


    private fun GetModules(projectId: Int) {
        val call = getClient().GetModules(projectId)

        call.enqueue(object : Callback<List<Module>> {
            override fun onResponse(call: Call<List<Module>>, response: Response<List<Module>>) {
                val modules = response.body()
                (view!!.rvModule.adapter as ModuleAdapter).modules = modules!!.toTypedArray()
            }

            override fun onFailure(call: Call<List<Module>>, t: Throwable) {
                Toast.makeText(
                    this@ModuleFragment.context,
                    getString(R.string.connection_title),
                    Toast.LENGTH_LONG
                ).show()
            }
        })

    }

}
