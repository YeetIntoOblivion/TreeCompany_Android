package com.spanishinquisition.treecompany.activitties

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.rest.RestClient
import kotlin.random.Random

class SplashScreenActivity : Activity() {
    private lateinit var loadingTv: TextView
    private lateinit var imageHome: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }


        initialiseViews()
    }

    fun initialiseViews() {

        loadingTv = findViewById(R.id.loadingCopyright)
        val stringList = resources.getStringArray(R.array.loadingScreen)
        loadingTv.text = stringList[Random.nextInt(stringList.size - 1)]
        imageHome = findViewById(R.id.treeHome)
        imageHome.setImageResource(R.drawable.logo)

        val t = Thread {
            Thread.sleep(7000)
            if (RestClient(this).isConnectedToServer()) {
                startActivity(Intent(this, HomeScreenActivity::class.java))
                finish()
            } else {
                finish()
            }
        }
        t.start()
    }

}

