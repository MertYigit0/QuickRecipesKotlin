package com.mertyigit0.quickrecipeskotlin.LoginRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mertyigit0.quickrecipeskotlin.R
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentLoginBinding
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentMealDetailBinding


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null;
    private val binding get() = _binding!!;

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = Firebase.auth
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        val view = binding.root;
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireView())

        binding.loginButton.setOnClickListener{

        }

        binding.registerNowText.setOnClickListener{
            navController.navigate(R.id.action_loginFragment2_to_registerFragment2)
        }


    }





}