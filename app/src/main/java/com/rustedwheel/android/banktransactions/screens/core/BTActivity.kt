package com.rustedwheel.android.banktransactions.screens.core

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

abstract class BTActivity : AppCompatActivity()  {

    protected fun presentActivity(clazz: KClass<out Activity>, bundle: Bundle = Bundle()) {
        val intent = Intent(this, clazz.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    protected fun showErrorMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

}