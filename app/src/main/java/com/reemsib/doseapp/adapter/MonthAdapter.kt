package com.reemsib.doseapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.reemsib.doseapp.R
import kotlinx.android.synthetic.main.month_item.view.*

open class MonthAdapter(var context: Context, var monthList: ArrayList<Int>) : BaseAdapter() {

    var mListener: OnItemClickListener? = null

    interface OnItemClickListener {

        fun onItemClick(position: Int, month: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun getCount(): Int {
        return monthList.size
    }

    override fun getItem(position: Int): Any {
        return monthList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val month = monthList[position]
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflator.inflate(R.layout.month_item, null)

        //  CompanyView.img_company.setImageResource(company.img)
        view.tv_month.text = "$month"
        view.setOnClickListener {
            if (mListener != null) {
                mListener!!.onItemClick(position, month)

            }

        }
        return view
    }

}