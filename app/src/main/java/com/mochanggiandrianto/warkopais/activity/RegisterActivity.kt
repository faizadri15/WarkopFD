package com.mochanggiandrianto.warkopais.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mochanggiandrianto.warkopais.MainActivity
import com.mochanggiandrianto.warkopais.R
import com.mochanggiandrianto.warkopais.app.ApiConfig
import com.mochanggiandrianto.warkopais.helper.SharredPref
import com.mochanggiandrianto.warkopais.model.ResponModel
import kotlinx.android.synthetic.main.activity_masuk.btn_register
import kotlinx.android.synthetic.main.activity_register.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var s: SharredPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        s = SharredPref(this)

        btn_register.setOnClickListener {
            register()
        }

        btn_google.setOnClickListener {
            dataDummy()
        }
    }

    fun dataDummy(){
        edt_name.setText("anggi")
        edt_email.setText("anggi@gmail.com")
        edt_phone.setText("0812837283736")
        edt_password.setText("1234567889")
    }

    fun register(){
        if (edt_name.text.isEmpty()){
            edt_name.error = "Kolom Nama Di isi dulu Napa"
            edt_name.requestFocus()
            return
        } else if (edt_email.text.isEmpty()){
            edt_email.error = "Kolom Email Di isi dulu Napa"
            edt_email.requestFocus()
            return
        } else if (edt_phone.text.isEmpty()){
            edt_phone.error = "Nomor Hp nya sayang isi dulu"
            edt_phone.requestFocus()
            return
        } else if (edt_password.text.isEmpty()){
            edt_password.error = "Isi aja yang susah diinget"
            edt_password.requestFocus()
            return
        }

        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.register(edt_name.text.toString(), edt_email.text.toString(), edt_phone.text.toString(), edt_password.text.toString()).enqueue(object : Callback<ResponModel>{

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@RegisterActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success == 1){
                    s.setStatusLogin(true)
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@RegisterActivity, "Selamat Datang Coy "+respon.user.name, Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@RegisterActivity, "Error:"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

        })


    }
}