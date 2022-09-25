package com.example.foodapp.model

import android.telecom.Call

class FoodData {
    //class model for recyclerview
    var count: String?=null
    var describe:String?=null
    var id: String?=null
    var img: String?=null
    var name: String?=null
    var price: String?=null

    constructor() {}
    constructor(count:String?,
                describe:String?, id: String?,
                img:String?, name:String?, price: String?){
        this.count=count
        this.describe =describe
        this.img=img
        this.id=id


        this.name=name
        this.price=price
    }
    //design layout


}