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
        holder.v.isFood = foodList[position]
        // get the data for view food
        val newList=foodList[position]
        val count=newList.count
        val describe= newList.describe
        val img=newList.img
        val name= newList.name
        val pstn= newList.id

        val price =newList.price





       holder.v.root.setOnClickListener{
            val mIntent = Intent(c, ViewFood::class.java)
            mIntent.putExtra("count",count)
            mIntent.putExtra("describe",describe)
            mIntent.putExtra("img",img)
           mIntent.putExtra("pstn",pstn)

            mIntent.putExtra("name",name)
            mIntent.putExtra("price",price)

            c.startActivity(mIntent)
        }


    }

    override fun getItemCount(): Int {
        return foodList.size
    }
    //this class automatically generated

}
//move to uitel class