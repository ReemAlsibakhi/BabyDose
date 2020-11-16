package com.reemsib.doseapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.reemsib.doseapp.R
import com.reemsib.doseapp.model.AppConstants
import com.shawnlin.numberpicker.NumberPicker
import kotlinx.android.synthetic.main.activity_drug_weight.*
import kotlinx.android.synthetic.main.activity_drug_weight.btn_cal
import kotlinx.android.synthetic.main.activity_drug_weight.img_info
import kotlinx.android.synthetic.main.activity_drug_weight.img_left
import kotlinx.android.synthetic.main.activity_drug_weight.number_picker
import kotlinx.android.synthetic.main.activity_drug_weight.tv_drug
import kotlinx.android.synthetic.main.activity_drug_weight.tv_month
import java.util.*

class DrugWeightActivity : AppCompatActivity(), View.OnClickListener,
    NumberPicker.OnValueChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drug_weight)

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)


        btn_cal.setOnClickListener(this)
        img_left.setOnClickListener(this)
        number_picker.setOnValueChangedListener(this)
        img_info.setOnClickListener (this)
        val data = intent
        val drug = data.getStringExtra("drug")!!

        tv_drug.text = drug


        number_picker.minValue = 2
        number_picker.maxValue = 35
        number_picker.setFormatter(NumberPicker.Formatter { value ->
            my_formatter(value)
        })

    }
    private fun my_formatter(value: Int) :String{
        return  String.format(Locale.ENGLISH,"%d", value)
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_cal -> {
                checkFields()
            }
            R.id.img_left -> {
                finish()
            }
            R.id.img_info ->{
                val i = Intent(this, AboutUsActivity::class.java)
                startActivity(i)
            }
        }
    }

    private fun checkFields() {
        if (tv_month.text.isEmpty() || tv_month.text=="0") {
            Toast.makeText(applicationContext, "please choose weight", Toast.LENGTH_LONG).show()
        } else {

            val i = Intent(this, ResultActivity::class.java)

            i.putExtra(AppConstants.DRUG_NAME, tv_drug.text.toString())
            i.putExtra(AppConstants.WEIGHT, tv_month.text.toString().toDouble())
            i.putExtra(AppConstants.LAYOUT, AppConstants.WEIGHT)
            startActivity(i)
        }
    }

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        Log.d(
            "WeightActivity",
            java.lang.String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal)
        )
        tv_month.text = "$newVal"
    }
}