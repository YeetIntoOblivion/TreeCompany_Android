package com.spanishinquisition.treecompany.activities

import android.content.Context
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

/*
 *  @author Edwin Kai-Yin Tam
 */

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

            if(email.isEmpty() || password.isEmpty()) {
                if (email.isEmpty()) {
                    loginEmailEt.error = "Email required"
                    loginEmailEt.requestFocus()

                }
                if (password.isEmpty()) {
                    loginPasswordEt.error = "Password required"
                    loginPasswordEt.requestFocus()
                }
                return@setOnClickListener
            }

            if (email == "jan@cityofideas.be" && password == "123456"){
                getSharedPreferences(getString(R.string.app_pref), Context.MODE_PRIVATE).edit().putBoolean("accountLoggedIn", true).apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
        loginRegisterBtn.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
