package com.mertyigit0.quickrecipeskotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mertyigit0.quickrecipeskotlin.R
import com.mertyigit0.quickrecipeskotlin.model.CategoriesModel

class CategoryAdapter(val categoryList : ArrayList<CategoriesModel>)
    :RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(var view : View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category,parent,false)
        return CategoryViewHolder(view)

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.itemCategoryText).text = categoryList[position].strCategory


    }

    fun updateCategoryList(newCategoryList:List<CategoriesModel>){
        categoryList.clear()
        categoryList.addAll(newCategoryList)
        notifyDataSetChanged()
    }


}