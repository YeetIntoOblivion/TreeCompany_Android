package com.spanishinquisition.treecompany.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.spanishinquisition.treecompany.fragments.IdeasFragment
import com.spanishinquisition.treecompany.fragments.IdeationFragment
import com.spanishinquisition.treecompany.fragments.QuestionnaireFragment


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
@Suppress("DEPRECATION")
class ModulePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    val mFragmentList = ArrayList<Fragment>()
    val mFragmentTitleList = ArrayList<String>()


    override fun getItem(position: Int): Fragment {



        return mFragmentList.get(position)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fm: Fragment, title:String){
        mFragmentList.add(fm);
        mFragmentTitleList.add(title);
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mFragmentTitleList.get(position);
    }

}