package com.reemsib.doseapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.reemsib.doseapp.R
import com.reemsib.doseapp.adapter.MonthAdapter
import com.reemsib.doseapp.model.AppConstants
import com.shawnlin.numberpicker.NumberPicker
import kotlinx.android.synthetic.main.activity_drug_age.*
import kotlinx.android.synthetic.main.layout_bottom_sheet.*
import java.util.*


class DrugAgeActivity : AppCompatActivity(), View.OnClickListener,
    NumberPicker.OnValueChangeListener {
    val monthList = ArrayList<Int>()
    var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drug_age)

        bottomSheetBehavior = BottomSheetBehavior.from(layoutBottomSheet)


        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)

        val data = intent
        val drug = data.getStringExtra("drug")!!

        tv_drug.text = drug

        btn_cal.setOnClickListener(this)
        img_left.setOnClickListener(this)
        number_picker.setOnValueChangedListener(this)
        tv_addMonth.setOnClickListener(this)
        img_info.setOnClickListener(this)
        img_picker.setOnClickListener{
         number_picker.setOnValueChangedListener(this)

        }
        for (i in 0 until 12) {
            monthList.add(i)
        }

        number_picker.minValue = 0
        number_picker.maxValue = 20
        number_picker.setFormatter(NumberPicker.Formatter { value ->
            my_formatter(value)
        })
        buildGridView()

    }

    private fun my_formatter(value: Int) :String{
      return  String.format(Locale.ENGLISH,"%d", value)
    }


    private fun buildGridView() {

        val adapter = MonthAdapter(this, monthList)
        month_grid.adapter = adapter

        adapter.setOnItemClickListener(object : MonthAdapter.OnItemClickListener {

            override fun onItemClick(position: Int, month: Int) {
                tv_month.text = "$month"
                bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        })
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_cal -> {
                checkFields()
            }
            R.id.img_left -> {
                finish()
            }
            R.id.tv_addMonth -> {
                showBottomSheet()
            }
            R.id.img_info -> {
                val i = Intent(this, AboutUsActivity::class.java)
                startActivity(i)
            }
        }
    }

    private fun checkFields() {
        if (tv_years.text == "0" && (tv_month.text == "0" || tv_month.text.isEmpty())) {
            Toast.makeText(applicationContext, "please enter age ", Toast.LENGTH_LONG).show()
        } else {
            val age_year = tv_years.text.toString()
            var age_month = tv_month.text.toString()
            if (age_month.isEmpty()) {
                age_month = "0"
            }
            val i = Intent(this, ResultActivity::class.java)
            i.putExtra(AppConstants.DRUG_NAME, tv_drug.text.toString())
            i.putExtra(AppConstants.AGE_YEAR, age_year.toInt())
            i.putExtra(AppConstants.AGE_MONTH, age_month.toInt())
            i.putExtra(AppConstants.LAYOUT, AppConstants.AGE)

            startActivity(i)
        }
    }


    private fun showBottomSheet() {

        if (bottomSheetBehavior!!.state == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_EXPANDED
        } else {
            bottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        Log.d(
            "AgeActivity",
            java.lang.String.format(Locale.US, "oldVal: %d, newVal: %d", oldVal, newVal)
        )
        tv_years.text = "$newVal"
    }
}