package com.spanishinquisition.treecompany.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.connectivity.RestClient
import java.io.Console
import java.io.IOException
import kotlin.random.Random

class SplashScreenActivity : Activity() {
    private lateinit var loadingTv: TextView
    private lateinit var imageHome: ImageView

    private var isConnected: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initialiseViews()
        testConnection()
    }

    private fun initialiseViews() {
        loadingTv = findViewById(R.id.loadingCopyright)
        val stringList = resources.getStringArray(R.array.loadingScreen)
        loadingTv.text = stringList[Random.nextInt(stringList.size - 1)]
        imageHome = findViewById(R.id.treeHome)
        imageHome.setImageResource(R.drawable.logo)
    }

    private fun testConnection() {
        if (RestClient(this).isConnectedToServer())
            runSplash()
        else
            showAlertDialog(this, R.string.connection_title, R.string.connection_msg)
    }

    private fun runSplash() {
        val t = Thread {
            run {
                try {
                    Thread.sleep(5000)
                } catch (e: InterruptedException) {
                    Log.d("Error", e.toString())
                } finally {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }
        }
        t.start()
    }

    private fun showAlertDialog(context: Context, title: Int, message: Int) {
        val builder: AlertDialog.Builder = context.let {
            AlertDialog.Builder(it)
        }

        builder.apply {
            setMessage(message)
            setTitle(title)
            setPositiveButton(R.string.connection_dialog_retry) { _, _ -> testConnection() }
            setNegativeButton(R.string.connection_dialog_close) { _, _ -> finish() }
            setCancelable(false)
        }

        builder.create().show()
    }

}



