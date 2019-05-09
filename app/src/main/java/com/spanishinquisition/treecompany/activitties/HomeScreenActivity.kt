package com.spanishinquisition.treecompany.activitties

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.spanishinquisition.treecompany.R

class HomeScreenActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_layout)
        setSupportActionBar(findViewById(R.id.home_toolbar))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.info -> {
            startActivity(Intent(this, AppInfoActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
