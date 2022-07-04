package com.mochanggiandrianto.warkopais.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SharedMemory
import android.view.View
import com.mochanggiandrianto.warkopais.R
import com.mochanggiandrianto.warkopais.helper.SharredPref
import kotlinx.android.synthetic.main.activity_masuk.*

class MasukActivity : AppCompatActivity() {

    lateinit var s:SharredPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masuk)

        s = SharredPref(this)

        mainButton()
    }

    private fun mainButton(){
        btn_prosesLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        btn_register.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}