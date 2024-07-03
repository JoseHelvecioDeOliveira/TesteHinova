package com.example.cozinhando.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.cozinhando.R

class ListAdapter(context: Context, dataArrayList: ArrayList<ListData>?) :
    ArrayAdapter<ListData>(context, R.layout.list_item, dataArrayList!!) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        var view = view
        val listData = getItem(position)


        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }

        if(listData != null) {

            val listImage = view!!.findViewById<ImageView>(R.id.listImage)
            val listName = view.findViewById<TextView>(R.id.listName)


            if (listData.image != null) {
                listImage.setImageBitmap(listData.image)
            }

            listName.text = listData.name
        }


        return view as View
    }
}