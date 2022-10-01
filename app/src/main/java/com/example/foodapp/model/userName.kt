package com.example.foodapp.model

import android.content.Context
import com.example.foodapp.foodordersrepository.FoodOrdersFragRepository

class userName(context: Context){

    val Preference_name=" Shared"
    val preference_count="user"
    val stan="stan"

    val preference= context.getSharedPreferences(Preference_name, Context.MODE_PRIVATE)
    fun getUser(): String{
        return preference.getString(preference_count, stan)!!
    }
    //set value
    fun setUser(count: String){
        val editor= preference.edit()
        editor.putString(preference_count, count)
        editor.apply()
    }
    /*var name:String?=null
     var phone:String?=null
     var email:String?=null
     var username:String?=null
    constructor(){}
    constructor(name: String?, username: String?, email:String?, phone:String?){
        this.name=name;
        this.username=username
        this.phone=phone
        this.email=email
    }
    fun return_name(): String {return name.toString()}
    fun return_uname(): String? {return username}
    fun return_email(): String? {return email}
    fun return_phone(): String? {return phone}
    *
     */

}
