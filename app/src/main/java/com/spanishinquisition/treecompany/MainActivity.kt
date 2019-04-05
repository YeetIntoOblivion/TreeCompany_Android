package com.spanishinquisition.treecompany

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var loadingTv : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        initialiseViews()
    }
    fun initialiseViews() {

        loadingTv = findViewById(R.id.blurbTv)
        val stringList = resources.getStringArray(R.array.loading_screen)
        loadingTv.text = stringList[Random.nextInt(stringList.size-1)]
    }
    }

