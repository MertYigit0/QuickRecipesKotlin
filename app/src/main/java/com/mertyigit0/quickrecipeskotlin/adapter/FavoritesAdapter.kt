package com.mertyigit0.quickrecipeskotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mertyigit0.quickrecipeskotlin.R
import com.mertyigit0.quickrecipeskotlin.model.MealModel
import com.mertyigit0.quickrecipeskotlin.util.downloadFromUrl
import com.mertyigit0.quickrecipeskotlin.util.placeholderProgressBar
import com.mertyigit0.quickrecipeskotlin.view.FavoritesFragmentDirections

class FavoritesAdapter(val favoritesList : ArrayList<MealModel>)
    :RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>()
{
        class FavoritesViewHolder(var view : View):RecyclerView.ViewHolder(view){

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.FavoritesViewHolder {
     val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_meal,parent,false)
        return  FavoritesViewHolder(view )
    }

    override fun onBindViewHolder(holder: FavoritesAdapter.FavoritesViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.itemMealText).text = favoritesList[position].strMeal

        holder.view.setOnClickListener{
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToMealDetailFragment(favoritesList[position].idMeal)
            Navigation.findNavController(it).navigate(action)
        }

        holder.view.findViewById<ImageView>(R.id.itemMealImageView).downloadFromUrl(favoritesList[position].strMealThumb, placeholderProgressBar(holder.view.context))



    }

    override fun getItemCount(): Int {
     return  favoritesList.size
    }

    fun updateFavoritesList(newFavoritesList : List<MealModel>){
        favoritesList.clear()
        favoritesList.addAll(newFavoritesList)
        notifyDataSetChanged()

    }





}