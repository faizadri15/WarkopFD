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
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var s:SharredPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharredPref(this)

        btn_login.setOnClickListener {
            login()
        }

    }

    private fun login(){
        if (edt_email.text.isEmpty()){
            edt_email.error = "Kolom Email Di isi dulu Napa"
            edt_email.requestFocus()
            return
        } else if (edt_password.text.isEmpty()){
            edt_password.error = "Isi aja yang susah diinget"
            edt_password.requestFocus()
            return
        }

        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.login(edt_email.text.toString(), edt_password.text.toString()).enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb.visibility = View.GONE
                Toast.makeText(this@LoginActivity, "Error:"+t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE
                val respon = response.body()!!
                if (respon.success == 1){
                    s.setStatusLogin(true)
                    s.setUser(respon.user)
//                    s.setString(s.name, respon.user.name)
//                    s.setString(s.phone, respon.user.phone)
//                    s.setString(s.email, respon.user.email)

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@LoginActivity, "Selamat Datang Coy "+respon.user.name, Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this@LoginActivity, "Error:"+respon.message, Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}