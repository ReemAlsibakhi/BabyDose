package com.reemsib.doseapp.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.reemsib.doseapp.R
import com.reemsib.doseapp.adapter.DrugAdapter
import com.reemsib.doseapp.model.AppConstants
import com.reemsib.doseapp.model.Drug
import kotlinx.android.synthetic.main.activity_main.*
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageButton


class MainActivity : AppCompatActivity(){
    private var mAdapter: DrugAdapter? = null
    private var mDrugList: ArrayList<Drug> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        this.getWindow()
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        et_search.requestFocus()

        createDrugList()
        buildRecyclerView()
        listenerEdit()

        val gib = GifImageButton(this)
        gib.setImageResource(R.drawable.waving_hand)
        img_info.setOnClickListener {
            val i = Intent(this, AboutUsActivity::class.java)
          startActivity(i)
        }
    }

    private fun buildRecyclerView() {
        mAdapter = DrugAdapter(this, mDrugList!!)
        rv_drugs.layoutManager = LinearLayoutManager(this)
        rv_drugs.adapter = mAdapter

        mAdapter!!.setOnItemClickListener(object : DrugAdapter.OnItemClickListener {
            override fun onClicked(clickedItemPosition: Int, drugName: String) {
                checkDrugName(drugName)
            }
        })
    }

    private fun createDrugList() {
        mDrugList = ArrayList<Drug>()

        val drugs = resources.getStringArray(R.array.drugs)
        for (i in 0 until drugs.size) {
            mDrugList!!.add(Drug(i, drugs.get(i)))
        }
    }

    private fun checkDrugName(drug: String) {
        if ((drug == getString(R.string.Chlorpheniramine_syr) ||
                    drug == getString(R.string.Salbutamol_inhale) ||
                    drug == getString(R.string.Salbutamol_syr2) ||
                    drug == getString(R.string.Beclomethasone_inhaler)) ||
            drug == getString(R.string.IRON_SYRUP50mg_elemental_iron_Prophylaxis) ||
            drug == getString(R.string.NYSTATIN_ORAL_SUSP)
//            drug== getString(R.string.IRON_SYRUP50mg_elemental_iron_Treatment)
        ) {
            //age
            val i = Intent(this, DrugAgeActivity::class.java)
            i.putExtra(AppConstants.DRUG_NAME, drug)
            startActivity(i)
//        }
        }else {
            //weight
            val i = Intent(this, DrugWeightActivity::class.java)
            i.putExtra(AppConstants.DRUG_NAME, drug)
            startActivity(i)
        }

    }

    private fun listenerEdit() {
        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int, after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })
    }

    private fun filter(text: String) {
        val filteredList: ArrayList<Drug> = ArrayList()
        for ( item :Drug in mDrugList!!) {
            if (item.name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)

        }
        mAdapter!!.filterList(filteredList)
    }

}
}
