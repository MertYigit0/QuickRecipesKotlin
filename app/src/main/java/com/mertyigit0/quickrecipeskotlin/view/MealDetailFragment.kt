package com.mertyigit0.quickrecipeskotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mertyigit0.quickrecipeskotlin.R
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentMealDetailBinding
import com.mertyigit0.quickrecipeskotlin.util.downloadFromUrl
import com.mertyigit0.quickrecipeskotlin.util.placeholderProgressBar
import com.mertyigit0.quickrecipeskotlin.viewmodel.InsideCategoryViewModel
import com.mertyigit0.quickrecipeskotlin.viewmodel.MealDetailViewModel


class MealDetailFragment : Fragment() {
    private var _binding: FragmentMealDetailBinding? = null;
    private val binding get() = _binding!!;

    private  lateinit var  viewModel: MealDetailViewModel
    private  var mealDetail = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentMealDetailBinding.inflate(inflater,container,false)
        val view = binding.root;
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
         //   mealDetail = MealDetailFragmentArgs.fromBundle(it)
        }


         viewModel = ViewModelProvider(this)[MealDetailViewModel::class.java]
        viewModel.getData( "52959")
        observeLiveData()

    }

    private  fun observeLiveData(){
        viewModel.mealDetailLiveData.observe(viewLifecycleOwner, Observer { mealDetails ->
            mealDetails?.let {

                binding.foodNameTextView.text = mealDetails.strMeal
                binding.instructionsTextView.text = mealDetails.strInstructions
                context?.let {
                    binding.foodImageView.downloadFromUrl(mealDetails.strMealThumb,
                        placeholderProgressBar(it))
                }



            }
        })
    }






}