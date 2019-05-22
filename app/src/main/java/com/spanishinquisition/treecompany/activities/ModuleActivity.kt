package com.spanishinquisition.treecompany.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.ModulePagerAdapter
import kotlinx.android.synthetic.main.activity_module.*

class ModuleActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_module)

        val fragmentAdapter = ModulePagerAdapter(supportFragmentManager)

       /* viewpager_main.adapter = fragmentAdapter

        tabs_main.setupWithViewPager(viewpager_main)*/
    }
}