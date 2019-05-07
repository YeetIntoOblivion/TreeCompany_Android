package com.spanishinquisition.treecompany

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : Activity() {
    private lateinit var loadingTv : TextView
    private lateinit var imageHome : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        val t = Thread{
            Thread.sleep(5000)

            val intent =  Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
        t.start()


    }

    }

