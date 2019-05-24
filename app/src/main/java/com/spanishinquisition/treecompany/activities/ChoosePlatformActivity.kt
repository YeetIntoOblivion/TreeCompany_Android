package com.spanishinquisition.treecompany.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.adapters.PlatformAdapter
import com.spanishinquisition.treecompany.models.Platform
import com.spanishinquisition.treecompany.rest.getClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChoosePlatformActivity : AppCompatActivity(), PlatformAdapter.OnPlatformSelectedListener {
    private lateinit var firstTimeExplain: TextView
    private lateinit var firstTimeRV: RecyclerView
    private lateinit var firstTimeCurrentSelection: TextView
    private lateinit var firstTimeAcceptBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_platform)
        initialiseViews()
        addEventHandlers()
        getPlatforms()
        switchText()
    }

    private fun initialiseViews() {
        firstTimeRV = findViewById(R.id.firstTimeRV)
        firstTimeExplain = findViewById(R.id.firstTimeExplain)
        firstTimeCurrentSelection = findViewById(R.id.firstTimeCurrentSelection)
        firstTimeRV.apply {
            layoutManager = LinearLayoutManager(this@ChoosePlatformActivity)
            adapter = PlatformAdapter(this@ChoosePlatformActivity)
        }
        firstTimeAcceptBtn = findViewById(R.id.firstTimeAcceptBtn)
    }

    private fun addEventHandlers() {
        firstTimeAcceptBtn.setOnClickListener {
            getSharedPreferences(getString(R.string.app_pref),Context.MODE_PRIVATE).edit().putBoolean(getString(R.string.pref_platform), false).apply()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun getPlatforms() {
        val call = getClient().getPlatforms()
        call.enqueue(object : Callback<List<Platform>> {
            override fun onResponse(call: Call<List<Platform>>, response: Response<List<Platform>>) {
                val platforms = response.body()
                (firstTimeRV.adapter as PlatformAdapter).platforms = platforms!!.toTypedArray()
            }

            override fun onFailure(call: Call<List<Platform>>, t: Throwable) {
                Toast.makeText(
                    this@ChoosePlatformActivity,
                    getString(R.string.dialog_connection_title),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    private fun switchText() {
        val firstTime = getSharedPreferences(getString(R.string.app_pref),Context.MODE_PRIVATE).getBoolean(getString(R.string.pref_platform), true)
        if (!firstTime) {
            firstTimeExplain.text = getString(R.string.x_time_selection)
            val platformName = getSharedPreferences(
                getString(R.string.app_pref),
                Context.MODE_PRIVATE
            ).getString(getString(R.string.pref_platform_name), null)
            firstTimeCurrentSelection.text = getString(R.string.first_time_selection_item, platformName)
        }
    }

    override fun onPlatformSelected(platform: Platform) {
        getSharedPreferences(getString(R.string.app_pref),Context.MODE_PRIVATE).edit().putInt(getString(R.string.pref_platform_id),platform.id).apply()
        getSharedPreferences(getString(R.string.app_pref),Context.MODE_PRIVATE).edit().putString(getString(R.string.pref_platform_name),platform.name).apply()
        firstTimeCurrentSelection.text = getString(R.string.first_time_selection_item, platform.name)
    }
}
