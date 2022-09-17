package com.example.foodapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.R
import com.example.foodapp.ViewFood
import com.example.foodapp.databinding.ItemListBinding
import com.example.foodapp.model.FoodData

class foodAdapter (
    var c: Context,var foodList:ArrayList<FoodData>


):RecyclerView.Adapter<foodAdapter.foodViewHolder>(){
    inner class foodViewHolder(val v: ItemListBinding): RecyclerView.ViewHolder(v.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): foodViewHolder {
        //
        val inflater= LayoutInflater.from(parent.context)
        val v =DataBindingUtil.inflate<ItemListBinding>(inflater, R.layout.item_list, parent, false)
        return foodViewHolder(v)
    }

    override fun onBindViewHolder(holder: foodViewHolder, position: Int) {
        holder.v.isFoods = foodList[position]
        // get the data for view food
        val newList=foodList[position]
        val img=newList.img
        val info=newList.info
        val name= newList.name
        val details= newList.details
        val price =newList.price
        val count=newList.count




        holder.v.root.setOnClickListener{
            val mIntent = Intent(c, ViewFood::class.java)
            mIntent.putExtra("img",img)
            mIntent.putExtra("info",info)
            mIntent.putExtra("name",name)
            mIntent.putExtra("details",details)
            mIntent.putExtra("price",price)
            mIntent.putExtra("count",count)
            c.startActivity(mIntent)
        }


    }

    override fun getItemCount(): Int {
        return foodList.size
    }
    //this class automatically generated

}
//move to uitel class