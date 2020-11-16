package com.reemsib.doseapp.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reemsib.doseapp.R
import com.reemsib.doseapp.model.Drug
import kotlinx.android.synthetic.main.drug_item.view.*

class DrugAdapter(var activity: Activity, var data: ArrayList<Drug>) :
    RecyclerView.Adapter<DrugAdapter.MyViewHolder>() {

    var mListener: OnItemClickListener?=null

    interface OnItemClickListener {
        fun onClicked(clickedItemPosition: Int, drugName: String)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val drug_name = itemView.tv_drug
        var cardView=itemView.cardview
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(activity).inflate(R.layout.drug_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

       holder.drug_name.text = data[position].name

        holder.cardView.setOnClickListener {
            if (mListener != null) {
                mListener!!.onClicked(position, data[position].name)

            }
        }
    }
    fun filterList(filteredList: ArrayList<Drug>) {
        data = filteredList
        notifyDataSetChanged()
    }
}

