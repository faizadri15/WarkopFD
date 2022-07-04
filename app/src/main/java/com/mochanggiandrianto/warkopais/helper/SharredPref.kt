package com.mochanggiandrianto.warkopais.helper

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mochanggiandrianto.warkopais.model.User

class SharredPref(activity: Activity) {

    private val login = "Login"
    val name = "name"
    val phone = "phone"
    val email = "email"

    val user = "user"

    private val mypref = "MAIN_PRF"
    private val sp:SharedPreferences = activity.getSharedPreferences(mypref, Context.MODE_PRIVATE)

    fun setStatusLogin(status:Boolean){
        sp.edit().putBoolean(login, status).apply()
    }

    fun getStatusLogin():Boolean{
        return sp.getBoolean(login, false)
    }

    fun setUser(value: User) {
        val data:String = Gson().toJson(value, User::class.java)
        sp.edit().putString(user, data).apply()
    }

    fun getUser() : User? {
        val data:String = sp.getString(user, null) ?: return null
        return Gson().fromJson<User>(data, User::class.java)
    }

    fun setString(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    fun getString(key: String): String{
        return sp.getString(key, "")!!
    }

}