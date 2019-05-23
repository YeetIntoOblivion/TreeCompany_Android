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

        /*       tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                   override fun onTabSelected(tab: TabLayout.Tab?) {
                       print(tab)
                   }

                   override fun onTabReselected(tab: TabLayout.Tab?) {
                   }

                   override fun onTabUnselected(tab: TabLayout.Tab?) {
                   }
               })*/


        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)

        toolbar.setTitle("Tab-munu in een modules")


        /*  val pageAdapter = ModulePagerAdapter(childFragmentManager)
          //viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
          //   viewpager.setAdapter(pageAdapter)*/

        return view;
    }


    private fun setUpViewPager(viewpager: ViewPager) {
        val adapter = ModulePagerAdapter(childFragmentManager);

      /*  var qFragment = QuestionnaireFragment()
        var args = Bundle()
        args.putInt("PROJECT_ID", projectId)

        qFragment.arguments = args*/

        val qFragment = QuestionnaireFragment()
        val iFragment = IdeationFragment()
        qFragment.projectId = projectId
        iFragment.projectId = projectId


        adapter.addFragment(qFragment, "Vragenlijst")
        adapter.addFragment(iFragment, "Ideeen")
        viewpager.adapter = adapter
    }
}
