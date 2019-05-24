package com.spanishinquisition.treecompany.activities

import android.content.Intent
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.IdeationAdapter
import com.spanishinquisition.treecompany.adapters.IdeationQuestionAdapter
import com.spanishinquisition.treecompany.adapters.ProjectsAdapter
import com.spanishinquisition.treecompany.fragments.*
import com.spanishinquisition.treecompany.models.projects.Project

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    ProjectsAdapter.OnProjectSelectedListener, IdeationAdapter.OnIdeationSelectionListener,
    IdeationQuestionAdapter.OnIdeationQuestionSelectionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
        switchFragments(HomeFragment())
    }


    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                switchFragments(HomeFragment())
                supportActionBar?.setTitle(R.string.menu_home)
            }
            R.id.nav_qr -> {
                // TODO maak een fragment voor mij b!tch
                supportActionBar?.setTitle(R.string.menu_qr)
            }
            R.id.nav_ideations -> {
                switchFragments(ModuleFragment())
                supportActionBar?.setTitle(R.string.menu_ideations)
            }
            R.id.nav_questionnaires -> {
                switchFragments(IdeasFragment())
                supportActionBar?.setTitle(R.string.menu_questionnaires)
            }
            R.id.nav_info -> {
//                // TODO maak een info fragment voor mij b!tch
//                supportActionBar?.setTitle(R.string.menu_info)
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_settings -> {
                switchFragments(SettingsFragment())
                supportActionBar?.setTitle(R.string.menu_settings)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun switchFragments(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainContent, fragment).commit()
    }

    override fun onProjectSelected(projectId: Int) {

        val moduleFragment = ModuleFragment()
        moduleFragment.projectId = projectId

        supportFragmentManager.beginTransaction().replace(R.id.mainContent, moduleFragment).addToBackStack("")
            .commit()
    }

    override fun onIdeationSelected(ideationId: Int) {

        val ideationQuestionFragment = IdeationQuestionFragment()
        ideationQuestionFragment.moduleId = ideationId

        supportFragmentManager.beginTransaction().replace(R.id.mainContent, ideationQuestionFragment).addToBackStack("")
            .commit()
    }

    override fun onIdeationQuestionSelected(iQuestionId: Int) {
        val ideasFragment = IdeasFragment()
        ideasFragment.iQuestionId = iQuestionId
        supportFragmentManager.beginTransaction().replace(R.id.mainContent, ideasFragment).addToBackStack("")
            .commit()
     }
}


