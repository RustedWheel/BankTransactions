package com.rustedwheel.android.banktransactions.screens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.rustedwheel.android.banktransactions.R
import com.rustedwheel.android.banktransactions.screens.core.BTActivity
import com.rustedwheel.android.banktransactions.screens.home.HomeActivity

class SplashActivity : BTActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_main)
        Handler(Looper.getMainLooper()).postDelayed({
            goToHomeScreen()
        }, 2000)
    }

    private fun goToHomeScreen() {
        presentActivity(HomeActivity::class)
        finishAffinity()
    }
}