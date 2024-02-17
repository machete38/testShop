package com.machete3845.testShop.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.machete3845.testShop.R


class PlaceholderFragment() : Fragment() {


    lateinit var tv: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.fragment_placeholder, container, false)

        tv = v.findViewById(R.id.tv_placeholder)
        var arg = arguments
        if (arg != null) {
            tv.text = arg.getString("text")
        }
        return v
    }



}