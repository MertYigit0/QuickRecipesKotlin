package com.mertyigit0.quickrecipeskotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mertyigit0.quickrecipeskotlin.R
import com.mertyigit0.quickrecipeskotlin.adapter.CategoryAdapter
import com.mertyigit0.quickrecipeskotlin.adapter.MealAdapter
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentInsideCategoryBinding
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentSearchHomeBinding
import com.mertyigit0.quickrecipeskotlin.viewmodel.InsideCategoryViewModel
import com.mertyigit0.quickrecipeskotlin.viewmodel.SearchHomeViewModel


class InsideCategoryFragment : Fragment() {
    private var _binding: FragmentInsideCategoryBinding? = null;
    private val binding get() = _binding!!;

    private lateinit var viewModel: InsideCategoryViewModel
    private val mealAdapter = MealAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentInsideCategoryBinding.inflate(inflater,container,false)
        val view = binding.root;
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[InsideCategoryViewModel::class.java]
        viewModel.refreshData()

        binding.mealListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mealListRecyclerView.adapter = mealAdapter

        observeLiveData()




    }


    fun observeLiveData(){
        viewModel.meals.observe(viewLifecycleOwner, Observer { meals ->
            meals?.let {
                binding.mealListRecyclerView.visibility = View.VISIBLE
                mealAdapter.updateMealList(meals)
            }
        })
        viewModel.mealError.observe(viewLifecycleOwner, Observer {error ->
            error?.let {
                if (it){

                }
                else{

                }
            }
        })


        fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }

    }




}