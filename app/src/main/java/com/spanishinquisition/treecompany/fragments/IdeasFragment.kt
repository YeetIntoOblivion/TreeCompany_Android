package com.spanishinquisition.treecompany.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.IdeaAdapter

class IdeasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ideas, container, false)


        view.findViewById<RecyclerView>(R.id.rvIdeas).apply {
            layoutManager = LinearLayoutManager(context)
/*
            adapter = IdeaAdapter(getIdeas(), context)
*/

        }




        return view;
    }


}
