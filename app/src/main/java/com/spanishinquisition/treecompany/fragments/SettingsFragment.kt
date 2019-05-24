package com.spanishinquisition.treecompany.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.activities.ChoosePlatformActivity
import kotlinx.android.synthetic.main.fragment_settings.view.*

/*
 *  @author Edwin Kai-Yin Tam
 */

class SettingsFragment : Fragment() {
    private lateinit var changePlatformBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        initialiseViews(view)
        addEventHandlers()
        return view
    }

    private fun initialiseViews(view: View) {
        changePlatformBtn = view.changePlatformBtn
    }

    private fun addEventHandlers() {
        changePlatformBtn.setOnClickListener {
            startActivity(Intent(activity, ChoosePlatformActivity::class.java))
        }
    }
}
