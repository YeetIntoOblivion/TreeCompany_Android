package com.spanishinquisition.treecompany.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.spanishinquisition.treecompany.R
import com.spanishinquisition.treecompany.models.User
import com.spanishinquisition.treecompany.rest.getClient
import retrofit2.Callback

import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var loginEmailEt: EditText
    private lateinit var loginPasswordEt: EditText
    private lateinit var loginForgotBtn: Button
    private lateinit var loginBtn: Button
    private lateinit var loginGoogleBtn: Button
    private lateinit var loginMicrosoftBtn: Button
    private lateinit var loginRegisterBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initialiseViews()
        addEventHandlers()
    }

    private fun initialiseViews() {
        loginEmailEt = findViewById(R.id.loginEmailEt)
        loginPasswordEt = findViewById(R.id.loginPasswordEt)
        loginForgotBtn = findViewById(R.id.loginForgotBtn)
        loginBtn = findViewById(R.id.loginBtn)
        loginGoogleBtn = findViewById(R.id.loginGoogleBtn)
        loginMicrosoftBtn = findViewById(R.id.loginMicrosoftBtn)
        loginRegisterBtn = findViewById(R.id.loginRegisterBtn)
    }

    private fun addEventHandlers() {
        loginForgotBtn.setOnClickListener {

        }
        loginBtn.setOnClickListener {

            val email = loginEmailEt.text.toString().trim()
            val password = loginPasswordEt.text.toString().trim()

            if (email.isEmpty()) {
                loginEmailEt.error = "Email required"
                loginEmailEt.requestFocus()
                return@setOnClickListener
            }


            if (password.isEmpty()) {
                loginPasswordEt.error = "Password required"
                loginPasswordEt.requestFocus()
                return@setOnClickListener
            }


            val call = getClient().userLogin(email, password)

            call.enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {

/*
                    response.body().user
*/
                }

            })

        }
        loginRegisterBtn.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        loginRegisterBtn.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
