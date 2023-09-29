package com.mertyigit0.quickrecipeskotlin.LoginRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.mertyigit0.quickrecipeskotlin.R
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentRegisterBinding
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentYouShouldLoginBinding


class YouShouldLoginFragment : Fragment() {
    private var _binding: FragmentYouShouldLoginBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding =   FragmentYouShouldLoginBinding.inflate(inflater,container,false)
        val view = binding.root;
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireView())



        binding.loginNowButton.setOnClickListener{

            navController.navigate(R.id.action_youShouldLoginFragment_to_loginFragment)


        }




    }

}