package com.machete3845.data

import android.content.Context
import android.content.SharedPreferences

class InternalDB {
    companion object{
        fun saveUserData(c: Context, obj: UserData) {
            val sp: SharedPreferences.Editor = c.getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit()
            sp.putString("fName", obj.fName)
            sp.putString("lName", obj.lName)
            sp.putString("phone", obj.phone)
            sp.apply()
        }
        fun getUserData(c: Context): UserData {
            val sp: SharedPreferences = c.getSharedPreferences("userInfo", Context.MODE_PRIVATE)
            val fName: String = sp.getString("fName","").toString()
            val lName: String = sp.getString("lName","").toString()
            val phone: String = sp.getString("phone","").toString()
            return UserData(fName, lName, phone)

        }
    }
}