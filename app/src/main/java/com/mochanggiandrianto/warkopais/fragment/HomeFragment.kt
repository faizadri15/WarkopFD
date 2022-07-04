package com.mochanggiandrianto.warkopais.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.mochanggiandrianto.warkopais.MainActivity
import com.mochanggiandrianto.warkopais.R
import com.mochanggiandrianto.warkopais.adapter.AdapterProduk
import com.mochanggiandrianto.warkopais.adapter.AdapterSlider
import com.mochanggiandrianto.warkopais.app.ApiConfig
import com.mochanggiandrianto.warkopais.model.Produk
import com.mochanggiandrianto.warkopais.model.ResponModel
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    lateinit var vpWarkop: ViewPager
    lateinit var rvProduk:RecyclerView
    lateinit var rvMinuman:RecyclerView
    lateinit var rvRoti:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        init(view)
        getProduk()

        return view
    }

    fun displayProduk(){
        val arrSlider = ArrayList<Int>()
        arrSlider.add(R.drawable.warkop1)
        arrSlider.add(R.drawable.warkop2)
        arrSlider.add(R.drawable.warkop3)

        val adapterSlider = AdapterSlider(arrSlider, activity)
        vpWarkop.adapter = adapterSlider

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager3 = LinearLayoutManager(activity)
        layoutManager3.orientation = LinearLayoutManager.HORIZONTAL

        rvProduk.adapter = AdapterProduk(requireActivity(), listProduk)
        rvProduk.layoutManager = layoutManager

        rvMinuman.adapter = AdapterProduk(requireActivity(), listProduk)
        rvMinuman.layoutManager = layoutManager2

        rvRoti.adapter = AdapterProduk(requireActivity(), listProduk)
        rvRoti.layoutManager = layoutManager3
    }

    private var listProduk:ArrayList<Produk> = ArrayList()
    fun getProduk(){
        ApiConfig.instanceRetrofit.getProduk().enqueue(object : Callback<ResponModel> {
            override fun onFailure(call: Call<ResponModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val res = response.body()!!
                if (res.success == 1){
                    listProduk = res.produks
                    displayProduk()
                }
            }

        })
    }

    fun init(view: View){
        vpWarkop = view.findViewById(R.id.vp_warkop)
        rvProduk = view.findViewById(R.id.rv_produk)
        rvMinuman = view.findViewById(R.id.rv_minuman)
        rvRoti = view.findViewById(R.id.rv_rotib)
    }

//        val arrProduk : ArrayList<Produk>get(){
//        val arr = ArrayList<Produk>()
//        val p1 = Produk()
//        p1.name = "Indomie Goreng Gurih"
//        p1.harga = "Rp. 15.000"
//        p1.image = R.drawable.ingoreng1
//
//        val p2 = Produk()
//        p2.name = "Indomie Goreng Bawang"
//        p2.harga = "Rp. 14.000"
//        p2.image = R.drawable.ingorengbw2
//
//        val p3 = Produk()
//        p3.name = "Indomie Rebus HOT"
//        p3.harga = "Rp. 17.000"
//        p3.image = R.drawable.inrebus1
//
//        val p4 = Produk()
//        p4.name = "Indomie Rebus Sikaee"
//        p4.harga = "Rp. 19.000"
//        p4.image = R.drawable.inrebuska2
//
//        arr.add(p1)
//        arr.add(p2)
//        arr.add(p3)
//        arr.add(p4)
//
//        return arr
//    }
//
//        val arrMinuman : ArrayList<Produk>get(){
//        val arr = ArrayList<Produk>()
//        val p1 = Produk()
//        p1.name = "Es Teh Seger"
//        p1.harga = "Rp. 4.000"
//        p1.image = R.drawable.minteh1
//
//        val p2 = Produk()
//        p2.name = "Es Tarik Ulur"
//        p2.harga = "Rp. 5.000"
//        p2.image = R.drawable.mintehtar2
//
//        val p3 = Produk()
//        p3.name = "Es Jeruk Purut"
//        p3.harga = "Rp. 7.000"
//        p3.image = R.drawable.minjeruk3
//
//        val p4 = Produk()
//        p4.name = "Es Kuku Kuda"
//        p4.harga = "Rp. 5.000"
//        p4.image = R.drawable.minkuku4
//
//        arr.add(p1)
//        arr.add(p2)
//        arr.add(p3)
//        arr.add(p4)
//
//        return arr
//    }
//
//        val arrRoti : ArrayList<Produk>get(){
//        val arr = ArrayList<Produk>()
//        val p1 = Produk()
//        p1.name = "Roti Polos Kyamu"
//        p1.harga = "Rp. 5.000"
//        p1.image = R.drawable.rotpo1
//
//        val p2 = Produk()
//        p2.name = "Roti CoklatJUUU"
//        p2.harga = "Rp. 14.000"
//        p2.image = R.drawable.rotcoke2
//
//        val p3 = Produk()
//        p3.name = "Roti Kolor Ijo"
//        p3.harga = "Rp. 17.000"
//        p3.image = R.drawable.rotijo3
//
//        val p4 = Produk()
//        p4.name = "Roti Lelehhhh"
//        p4.harga = "Rp. 10.000"
//        p4.image = R.drawable.rotmen4
//
//        arr.add(p1)
//        arr.add(p2)
//        arr.add(p3)
//        arr.add(p4)
//
//        return arr
//    }
}