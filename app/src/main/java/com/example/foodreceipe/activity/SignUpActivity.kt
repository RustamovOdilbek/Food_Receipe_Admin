package com.example.foodreceipe.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
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
 * In SignUpActivity, user can sighup using fullname, email, password
 */

class SignUpActivity : BaseActivty() {
    val TAG = SignUpActivity::class.java.simpleName
    lateinit var et_fullname: EditText
    lateinit var et_email: EditText
    lateinit var et_password: EditText
    lateinit var et_cpassword: EditText
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        context = this
        initViews()
    }

    private fun initViews() {
        et_fullname = findViewById(R.id.et_fullname)
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)
        et_cpassword = findViewById(R.id.et_cpassword)

        val b_signup = findViewById<Button>(R.id.b_signup)
        b_signup.setOnClickListener {
            firebaseSignUp(et_email.text.toString(), et_password.text.toString())
        }
        val tv_signin = findViewById<TextView>(R.id.tv_signin)
        tv_signin.setOnClickListener { finish() }
    }

    private fun firebaseSignUp(email: String, password: String) {
        AuthManager.signUp(email, password, object : AuthHandler {
            // dismissLoading()
            override fun onSuccess(uid: String) {
                callMainActivity(context)
                toast(getString(R.string.str_signup_success))
            }

            override fun onError(exception: Exception?) {
                // dismissLoading()
                toast(getString(R.string.str_signup_failed))
            }
        })
    }
}