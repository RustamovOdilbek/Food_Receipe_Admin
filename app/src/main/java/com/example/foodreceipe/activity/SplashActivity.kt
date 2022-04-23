package com.example.foodreceipe.activity

import android.os.Bundle
import android.os.CountDownTimer
import android.view.WindowManager
import com.example.foodreceipe.R
import com.example.foodreceipe.manager.AuthManager

/**
 * In SplashActivity, user can visit to SignInActivity or MainActivity
 */

class SplashActivity : BaseActivty() {
    val TAG = SplashActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)
        initViews()
    }

    private fun initViews() {
        countDownTimer()
    }

    private fun countDownTimer() {
        object : CountDownTimer(2000, 1000){
            override fun onTick(millisUnilFinished: Long) {}

            override fun onFinish() {
                if (AuthManager.isSignedIn()){
                    callMainActivity(this@SplashActivity)
                }else {
                    callSignInActivity(this@SplashActivity)
                }
            }
        }.start()
    }
}