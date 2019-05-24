package com.spanishinquisition.treecompany.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.Platform
import com.spanishinquisition.treecompany.rest.getClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChoosePlatformActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_platform)
        initialiseViews()
        addEventHandlers()
    }

    private fun initialiseViews() {

    }

    private fun addEventHandlers() {
        getPreferences(Context.MODE_PRIVATE).edit().putBoolean(getString(R.string.pref_platform_id), false).apply()

    }

    private fun getPlatforms() {
        val call = getClient().getPlatforms()
        call.enqueue(object : Callback<List<Platform>> {
            override fun onResponse(call: Call<List<Platform>>, response: Response<List<Platform>>) {
                platformItems = response.body()!!.toTypedArray()
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
}
