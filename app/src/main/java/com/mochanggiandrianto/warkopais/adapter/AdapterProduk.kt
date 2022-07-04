package com.mochanggiandrianto.warkopais.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.mochanggiandrianto.warkopais.MainActivity
import com.mochanggiandrianto.warkopais.R
import com.mochanggiandrianto.warkopais.activity.DetailProdukActivity
import com.mochanggiandrianto.warkopais.activity.LoginActivity
import com.mochanggiandrianto.warkopais.helper.Helper
import com.mochanggiandrianto.warkopais.model.Produk
import com.mochanggiandrianto.warkopais.util.Config
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class AdapterProduk(var activity: Activity, var data:ArrayList<Produk>):RecyclerView.Adapter<AdapterProduk.Holder>() {

    class Holder(view: View):RecyclerView.ViewHolder(view){
        val tvNama = view.findViewById<TextView>(R.id.tv_nama)
        val tvHarga = view.findViewById<TextView>(R.id.tv_harga)
        val imgProduk = view.findViewById<ImageView>(R.id.img_produk)
        val layout = view.findViewById<CardView>(R.id.layout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvNama.text = data[position].name
        holder.tvHarga.text = Helper().gantiRupiah(data[position].harga)
//        holder.imgProduk.setImageResource(data[position].image)
        val image = "http://192.168.1.43/warkop/public/storage/produk/" + data[position].image
        Picasso.get()
            .load(image)
            .placeholder(R.drawable.produk1)
            .error(R.drawable.produk1)
            .into(holder.imgProduk)

        holder.layout.setOnClickListener {
            val activiti = Intent(activity, DetailProdukActivity::class.java)
            val str = Gson().toJson(data[position], Produk::class.java)
            activiti.putExtra("extra", str)
            activity.startActivity(activiti)
        }
    }

}