package com.mertyigit0.quickrecipeskotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mertyigit0.quickrecipeskotlin.adapter.CategoryAdapter
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentSearchHomeBinding
import com.mertyigit0.quickrecipeskotlin.viewmodel.SearchHomeViewModel


class SearchHomeFragment : Fragment() {

    private var _binding: FragmentSearchHomeBinding? = null;
    private val binding get() = _binding!!;

    private lateinit var viewModel: SearchHomeViewModel
    private val categoryAdapter = CategoryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchHomeBinding.inflate(inflater,container,false)
        val view = binding.root;
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[SearchHomeViewModel::class.java]
        viewModel.refreshData()

        binding.categoryListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.categoryListRecyclerView.adapter = categoryAdapter

        observeLiveData()
    }



    fun observeLiveData(){
        viewModel.categories.observe(viewLifecycleOwner, Observer {categories ->
            categories?.let {
                binding.categoryListRecyclerView.visibility = View.VISIBLE
                categoryAdapter.updateCategoryList(categories)

            }

        })
        viewModel.categoryError.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it) {


                    //  binding.countryError.visibility = View.VISIBLE

                } else {
                   // categoryError.visibility = View.GONE

                }
            }
        })



    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}