package com.spanishinquisition.treecompany.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.ModulePagerAdapter


class ModuleFragment : Fragment() {


    var projectId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_module, container, false)
        // Setting ViewPager for each Tabs
        val viewpager = view.findViewById<ViewPager>(R.id.viewPager)
        setUpViewPager(viewpager)
        // Set Tabs inside Toolbar
        val tabs = view.findViewById<TabLayout>(R.id.tablayout)

        tabs.setupWithViewPager(viewpager)

        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        toolbar.setTitle("Vragenlijsten & Ideations")

        return view;
    }


    private fun setUpViewPager(viewpager: ViewPager) {
        val adapter = ModulePagerAdapter(childFragmentManager);
        val qFragment = QuestionnaireFragment()
        val iFragment = IdeationFragment()
        qFragment.projectId = projectId
        iFragment.projectId = projectId


        adapter.addFragment(qFragment, "Vragenlijst")
        adapter.addFragment(iFragment, "Ideeen")
        viewpager.adapter = adapter
    }
}
