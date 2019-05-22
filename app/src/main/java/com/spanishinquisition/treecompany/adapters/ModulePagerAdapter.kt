package com.spanishinquisition.treecompany.adapters



import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.spanishinquisition.treecompany.fragments.IdeasFragment
import com.spanishinquisition.treecompany.fragments.QuestionnaireFragment


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
@Suppress("DEPRECATION")
class ModulePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                QuestionnaireFragment()
            }
            else -> {
                return IdeasFragment()
            }
        }
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return PlaceholderFragment.newInstance(position + 1)
    }

    override fun getPageTitle(position: Int): CharSequence? {

        return when (position) {
            0 -> "vragenlijst"
            else -> {
                return "ideeen"
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}