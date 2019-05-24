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
import com.spanishinquisition.treecompany.rest.isConnectedToServer
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.random.Random

/*
 *  @authors Sacha Buelens & Edwin Kai-Yin Tam
 */

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

/*
 * Deze methodes proberen om een verbinding te maken met de server. Slaagt het dan wordt er gezien of de app al eerder
 * heeft gerunt. Eerste keer dat het opstart -> ChoosePlatformActivity. Zoveelste keer dat het opstart -> MainActivity.
 * Lukt het toch niet om verbinding te maken met de server dan komt de pop-op om te vragen of er opnieuw moet worden
 * geprobeerd.
 */

    //region Verify backend connection

    private fun contactBackEnd() {
        val observable = Observable.create<Boolean> { emitter ->
            emitter.onNext(isConnectedToServer(this))
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
            val firstTime = getSharedPreferences(getString(R.string.app_pref),Context.MODE_PRIVATE).getBoolean(getString(R.string.pref_platform), true)

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

    //endregion

    override fun onStop() {
        super.onStop()
        if (!localhostSubscription.isDisposed)
            localhostSubscription.dispose()
    }
}



