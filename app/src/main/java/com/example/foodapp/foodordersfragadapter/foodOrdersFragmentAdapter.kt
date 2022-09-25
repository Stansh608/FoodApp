package com.example.foodapp.foodordersfragadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.foodordersfragmodel.FoodOrdersFragDataClass
import com.example.foodapp.foodordersrepository.FoodOrdersFragRepository

class foodOrdersFragmentAdapter :
    RecyclerView.Adapter<foodOrdersFragmentAdapter.foodordersViewHolder>() {


    private val orderList = ArrayList<FoodOrdersFragDataClass>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): foodordersViewHolder {
        val orderView=  LayoutInflater.from(parent.context).inflate(
            R.layout.foodorderslist, parent, false
        )
        return foodordersViewHolder(orderView)
    }

    override fun onBindViewHolder(holder: foodordersViewHolder, position: Int) {

        val currentorder= orderList[position]
        holder.foodname.text=currentorder.mealname
        holder.foodprice.text=currentorder.totalprice
        holder.foodcount.text=currentorder.items
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
    fun updateOrderList(orderList: List<FoodOrdersFragDataClass>){
        this.orderList.clear()
        this.orderList.addAll(orderList)
        notifyDataSetChanged()
    }
    //set the values
    class foodordersViewHolder(orderView: View) : RecyclerView.ViewHolder(orderView){
        val foodname: TextView = orderView.findViewById(R.id.tvmealname)
        val foodprice: TextView = orderView.findViewById(R.id.tvprice)
        val foodcount: TextView = orderView.findViewById(R.id.tvcount)

    }

}