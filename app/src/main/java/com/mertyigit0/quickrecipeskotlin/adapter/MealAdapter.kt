package com.mertyigit0.quickrecipeskotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mertyigit0.quickrecipeskotlin.R
import com.mertyigit0.quickrecipeskotlin.model.MealModel
import com.mertyigit0.quickrecipeskotlin.util.downloadFromUrl
import com.mertyigit0.quickrecipeskotlin.util.placeholderProgressBar
import com.mertyigit0.quickrecipeskotlin.view.InsideCategoryFragmentDirections

class MealAdapter(val mealList: ArrayList<MealModel>)
    :RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    class MealViewHolder(var view : View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_meal,parent,false)
        return  MealViewHolder(view)


    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.itemMealText).text = mealList[position].strMeal

        holder.view.setOnClickListener {
            val action = InsideCategoryFragmentDirections.actionInsideCategoryFragmentToMealDetailFragment(mealList[position].idMeal)
            Navigation.findNavController(it).navigate(action)
        }



        holder.view.findViewById<ImageView>(R.id.itemMealImageView).downloadFromUrl(mealList[position].strMealThumb, placeholderProgressBar(holder.view.context))

    }



    fun updateMealList(newMealList: List<MealModel>){
        mealList.clear()
        mealList.addAll(newMealList)
        notifyDataSetChanged()
    }



}