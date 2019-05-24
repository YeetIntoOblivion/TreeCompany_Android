package com.spanishinquisition.treecompany.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.rest.SplashConnection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.random.Random

class SplashScreenActivity : Activity() {
    private lateinit var loadingTv: TextView
    private lateinit var imageHome: ImageView
    private lateinit var localhostSubscription: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initialiseViews()
        runSplash()
    }

    private fun initialiseViews() {
        loadingTv = findViewById(R.id.loadingCopyright)
        val stringList = resources.getStringArray(R.array.loadingScreen)
        loadingTv.text = stringList[Random.nextInt(stringList.size - 1)]
        imageHome = findViewById(R.id.treeHome)
        imageHome.setImageResource(R.drawable.logo)
    }

    private fun runSplash() {
        val t = Thread {
            run {
                try {
                    Thread.sleep(4000)
                } catch (e: InterruptedException) {
                    Log.d("Error", e.toString())
                } finally {
                    contactBackEnd()
                }
            }
        }
        t.start()
    }

    private fun contactBackEnd() {
        val observable = Observable.create<Boolean> { emitter ->
            emitter.onNext(SplashConnection(this).isConnectedToServer())
        }

        localhostSubscription = observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { isConnected ->
                verifyContact(isConnected)
            }
    }

    private fun verifyContact(isConnected: Boolean) {
        if (isConnected) {
            val firstTime = getPreferences(Context.MODE_PRIVATE).getBoolean(getString(R.string.pref_platform_id), true)

            if (firstTime)
                startActivity(Intent(this, ChoosePlatformActivity::class.java))
            else
                startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            showConnectionFailedDialog()
        }
    }

    private fun showConnectionFailedDialog() {
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }
        builder.apply {
            setMessage(R.string.dialog_connection_msg)
            setTitle(R.string.dialog_connection_title)
            setPositiveButton(R.string.dialog_connection_retry) { _, _ -> contactBackEnd() }
            setNegativeButton(R.string.dialog_connection_shutdown) { _, _ -> finish() }
            setCancelable(false)
        }
        builder.create().show()
    }

    override fun onStop() {
        super.onStop()
        if (!localhostSubscription.isDisposed)
            localhostSubscription.dispose()
    }
}



