package com.mochanggiandrianto.warkopais.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mochanggiandrianto.warkopais.MainActivity
import com.mochanggiandrianto.warkopais.R
import com.mochanggiandrianto.warkopais.activity.LoginActivity
import com.mochanggiandrianto.warkopais.helper.SharredPref

/**
 * A simple [Fragment] subclass.
 */
class AkunFragment : Fragment() {

    private lateinit var s:SharredPref
    private lateinit var btnLogout:TextView
    private lateinit var tvName:TextView
    private lateinit var tvEmail:TextView
    private lateinit var tvPhone:TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_akun, container, false)
        init(view)

        s = SharredPref(requireActivity())

        btnLogout.setOnClickListener {
            s.setStatusLogin(false)
        }

        setData()
        return view
    }

    private fun setData(){

        if (s.getUser() == null){
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            return
        }

        val user = s.getUser()!!

        tvName.text = user.name
        tvEmail.text = user.email
        tvPhone.text = user.phone
    }

    private fun init(view: View) {
        btnLogout = view.findViewById(R.id.btn_logout)
        tvName = view.findViewById(R.id.tv_name)
        tvEmail = view.findViewById(R.id.tv_email)
        tvPhone = view.findViewById(R.id.tv_phone)
    }
}