package com.reemsib.doseapp.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.reemsib.doseapp.R
import com.reemsib.doseapp.adapter.MonthAdapter
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.activity_about_us.adView
import kotlinx.android.synthetic.main.activity_about_us.img_left
import kotlinx.android.synthetic.main.activity_drug_weight.*
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.layout_bottom_sheet.*
import kotlin.collections.ArrayList


class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        img_left.setOnClickListener{
            finish()
        }
//        developerName.setOnClickListener {
//            val uri: Uri =
//                Uri.parse("https://mostaql.com/u/ReemSibakhi")
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
//        }
    }

}