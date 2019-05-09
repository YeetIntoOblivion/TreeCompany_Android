package com.spanishinquisition.treecompany.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.spanishinquisition.treecompany.R

class LoginActivity : AppCompatActivity() {
    private lateinit var loginEmailEt : EditText
    private lateinit var loginPasswordEt : EditText
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
    }

    private fun addEventHandlers() {
        loginForgotBtn.setOnClickListener{

        }
        loginBtn.setOnClickListener{

        }
    }
}
