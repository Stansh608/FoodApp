package com.example.foodapp.model

class FoodData {
    //class model for recyclerview
    var name: String?=null
    var img: String?=null
    var info: String?=null
    constructor() {}
    constructor(name:String?,
    info:String?,
    img:String?){
        this.name=name
        this.info =info
        this.img=img
    }
    //design layout


}