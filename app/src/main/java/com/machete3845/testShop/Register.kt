package com.machete3845.testShop

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.machete3845.data.InternalDB
import com.machete3845.data.UserData

class Register : AppCompatActivity() {

    lateinit var et_fName: EditText
    lateinit var et_lName: EditText
    lateinit var et_phone: EditText

    lateinit var cr_fName: ImageView
    lateinit var cr_lName: ImageView
    lateinit var cr_phone: ImageView

    lateinit var btn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        et_fName = findViewById(R.id.et_fName)
        et_lName = findViewById(R.id.et_lName)
        et_phone = findViewById(R.id.et_phone)

        cr_phone = findViewById(R.id.iv_cr_phone)
        cr_fName = findViewById(R.id.iv_cr_fName)
        cr_lName = findViewById(R.id.iv_cr_lName)

        btn = findViewById(R.id.bt_login)

        setupMask()

        setupTextDel(et_fName, cr_fName)
        setupTextDel(et_lName, cr_lName)
        setupTextDel(et_phone, cr_phone, false)


    }


    private fun isCyrillic(s: String): Boolean {
        for (a in s.toCharArray()) {
            if (Character.UnicodeBlock.of(a) !== Character.UnicodeBlock.CYRILLIC) {
                return false
            }
        }
        return true
    }
    private fun setupTextDel(et: EditText, iv: ImageView, checkCyr: Boolean = true) {
        iv.setOnClickListener { et.setText("") }
        et.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (checkCyr)
                {
                    checkCyrillic(et)
                }
                if (et.text.isEmpty())
                {
                    iv.visibility = View.GONE
                }
                else
                {
                    iv.visibility = View.VISIBLE
                }
                checkIfComplete()
            }

        })
    }

    private fun checkCyrillic(et: EditText) {
        if (!isCyrillic(et.text.toString()))
        {
            et.setBackgroundResource(R.drawable.custom_edittexterror)
        }
        else
        {
            et.setBackgroundResource(R.drawable.custom_container)
        }
    }

    private fun checkIfComplete() {
        if (et_fName.text.isNotEmpty() && et_lName.text.isNotEmpty() && isCyrillic(et_fName.text.toString()) && isCyrillic(et_lName.text.toString()) && et_phone.text.length == 16)
        {
            btn.background.setTint(getColor(R.color.pink))
            btn.setOnClickListener {
                InternalDB.saveUserData(this, UserData(et_fName.text.toString(),et_lName.text.toString(), et_phone.text.toString()))
                val i = Intent(this, MainPage::class.java)
                startActivity(i)
            }
        }
        else
        {
            btn.background.setTint(getColor(R.color.light_pink))
            btn.setOnClickListener {  }
        }
    }

    private fun setupMask()
    {
        et_phone.setOnFocusChangeListener(View.OnFocusChangeListener { v, hasFocus ->
            if (hasFocus)
            {
                et_phone.setHint("+7 XXX XXX-XX-XX")
                et_phone.setBackgroundResource(R.drawable.custom_container)
            }
            else
            {
                et_phone.setHint(getString(R.string.phone))
                if (et_phone.text.length != 16)
                {
                    et_phone.setBackgroundResource(R.drawable.custom_edittexterror)
                }
            }
        })

}
}