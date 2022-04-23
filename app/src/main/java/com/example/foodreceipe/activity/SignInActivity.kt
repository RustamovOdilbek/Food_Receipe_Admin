package com.example.foodreceipe.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.foodreceipe.R
import com.example.foodreceipe.manager.AuthManager
import com.example.foodreceipe.manager.handler.AuthHandler
import com.example.foodreceipe.utils.Extensions.toast
import java.lang.Exception

/**
 * In SignInActivity, user can login using email, password
 */

class SignInActivity : BaseActivty() {
    val TAG = SignInActivity::class.java.simpleName

    lateinit var et_email: EditText
    lateinit var et_password: EditText

    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initViews()
    }

    private fun initViews() {
        et_email = findViewById(R.id.et_email)
        et_password  =findViewById(R.id.et_password)
        val b_signin = findViewById<Button>(R.id.b_signin)
        b_signin.setOnClickListener {
            val email = et_email.text.toString()
            val password = et_password.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseSignIn(email, password)
            }
        }
        val tv_signup = findViewById<TextView>(R.id.tv_signup)
        tv_signup.setOnClickListener { callSignUpActivity() }
    }

    private fun firebaseSignIn(email: String, password: String){
        // showLoading(this)
        AuthManager.signIn(email, password, object : AuthHandler {
            override fun onSuccess(uid: String) {
                // dismissLoading()
                toast(getString(R.string.str_signin_success))
                callMainActivity(context)
            }

            override fun onError(exception: Exception?) {
                // dismissLoading()
                toast(getString(R.string.str_signin_failed))
            }
        })
    }

    private fun callSignUpActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
        finish()
    }
}