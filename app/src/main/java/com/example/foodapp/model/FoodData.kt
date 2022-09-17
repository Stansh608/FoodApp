package com.example.foodapp.model

import android.telecom.Call

class FoodData {
    //class model for recyclerview
    var name: String?=null
    var img: String?=null
    var info: String?=null
    var details:String?=null
    var count: String?=null
    var price: String?=null
    constructor() {}
    constructor(name:String?,
    info:String?,

    img:String?,details:String?, price:String?,count:String?){
        this.name=name
        this.info =info
        this.img=img
        this.details=details
        this.price=price
        this.count=count
    }
    //design layout


}