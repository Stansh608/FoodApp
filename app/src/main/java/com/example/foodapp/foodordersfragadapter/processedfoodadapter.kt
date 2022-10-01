package com.example.foodapp.foodordersfragadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.foodordersfragmodel.FoodOrdersFragDataClass
import com.example.foodapp.foodordersfragmodel.processedfooddataclass

class processedfoodadapter:
    RecyclerView.Adapter<processedfoodadapter.processedfoodViewHolder>() {


    private val processedfoodList = ArrayList<processedfooddataclass>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): processedfoodViewHolder {
        val orderView=  LayoutInflater.from(parent.context).inflate(
            R.layout.processedfoodlist, parent, false
        )
        return processedfoodViewHolder(orderView)
    }

    override fun onBindViewHolder(holder: processedfoodViewHolder, position: Int) {

        val currentprocessed= processedfoodList[position]
        holder.foodname1.text=currentprocessed.mealname
        holder.foodprice1.text=currentprocessed.totalprice
        holder.foodcount1.text=currentprocessed.items
    }

    override fun getItemCount(): Int {
        return processedfoodList.size
    }
    fun updateprocessedList(processedList: List<processedfooddataclass>){
        this.processedfoodList.clear()
        this.processedfoodList.addAll(processedList)
        notifyDataSetChanged()
    }
    //set the values
    class processedfoodViewHolder(processedView: View) : RecyclerView.ViewHolder(processedView){
        val foodname1: TextView = processedView.findViewById(R.id.tvmealname1)
        val foodprice1: TextView = processedView.findViewById(R.id.tvprice1)
        val foodcount1: TextView = processedView.findViewById(R.id.tvcount1)

    }

}