package com.spanishinquisition.treecompany.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.Project
import com.spanishinquisition.treecompany.rest.GetRetroFit

import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response


class ConnectActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        connect()
    }


    fun connect() {

        val builder = AlertDialog.Builder(this@ConnectActivity)


        val service = GetRetroFit()
        val call = service.GetAllByPlatform(1);

        call.enqueue(object : Callback<List<Project>>{
            override fun onResponse(call: Call<List<Project>>?, response: Response<List<Project>>?) {

                builder.setMessage("GELUKT")

            }

            override fun onFailure(call: Call<List<Project>>?, t: Throwable?) {
                builder.setMessage("NIET GELUKT")
            }
        })

    }
}