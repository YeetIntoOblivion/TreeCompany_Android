package com.spanishinquisition.treecompany.activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.Platform
import com.spanishinquisition.treecompany.rest.SplashConnection
import com.spanishinquisition.treecompany.rest.getClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.random.Random
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreenActivity : Activity() {
    private lateinit var loadingTv: TextView
    private lateinit var imageHome: ImageView
    private lateinit var localhostSubscription: Disposable
    private lateinit var platformItems: Array<Platform>

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
            getPlatforms()
            getPrefPlatform()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            showConnectionFailedDialog()
        }
    }

    private fun getPrefPlatform() {
        val firstTime = getPreferences(Context.MODE_PRIVATE).getBoolean(getString(R.string.pref_platform_id), true)
        if (firstTime) {
            setPrefPlatformDialog()
            getPreferences(Context.MODE_PRIVATE).edit().putBoolean(getString(R.string.pref_platform_id), false).apply()
        }
    }

    private fun setPrefPlatformDialog() {
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }
        builder.apply {
            setTitle(R.string.dialog_platform_pref_title)
            //TODO MAAK DIT AF
//            setItems()
            setCancelable(false)
        }
        builder.create().show()
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

    private fun getPlatforms() {
        val call = getClient().getPlatforms()
        call.enqueue(object : Callback<List<Platform>> {
            override fun onResponse(call: Call<List<Platform>>, response: Response<List<Platform>>) {
                platformItems = response.body()!!.toTypedArray()
            }

            override fun onFailure(call: Call<List<Platform>>, t: Throwable) {
                Toast.makeText(
                    this@SplashScreenActivity,
                    getString(R.string.dialog_connection_title),
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    override fun onStop() {
        super.onStop()
        if (!localhostSubscription.isDisposed)
            localhostSubscription.dispose()
    }
}



