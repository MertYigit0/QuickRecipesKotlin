package com.mertyigit0.quickrecipeskotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.mertyigit0.quickrecipeskotlin.MainActivity
import com.mertyigit0.quickrecipeskotlin.R
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentProfileBinding
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentShareMyRecipesBinding
import com.mertyigit0.quickrecipeskotlin.viewmodel.LoginCheckViewModel


class ShareMyRecipesFragment : Fragment() {

    private lateinit var viewModel: LoginCheckViewModel
    private var _binding:FragmentShareMyRecipesBinding? = null;
    private val binding get() = _binding!!;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // MainActivity içinde oluşturulan Singleton ViewModel'e erişim
        viewModel = (requireActivity() as MainActivity).viewModelLoginCheck
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding =   FragmentShareMyRecipesBinding.inflate(inflater,container,false)
        val view = binding.root;
        return view

    }

    override fun onResume() {
        super.onResume()
     //setupObservers()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.DoLogin.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
        }
        binding.b1.setOnClickListener{
            val navController = view?.findNavController()
            navController?.navigate(R.id.recipeFeedFragment)

        }
        binding.b2.setOnClickListener{
            val navController = view?.findNavController()
            navController?.navigate(R.id.shareMyRecipesFragment)
        }
        binding.b3.setOnClickListener{
            val navController = view?.findNavController()
            navController?.navigate(R.id.favoritesFragment)

        }
        binding.b4.setOnClickListener{
            val navController = view?.findNavController()
            navController?.navigate(R.id.profileFragment)
        }
    }
    private fun setupObservers() {

        // Giriş durumunu dinle
        viewModel.isLoggedIn.observe(viewLifecycleOwner, Observer { isLoggedIn ->
            if (isLoggedIn) {
                // Kullanıcı giriş yapmış
                Toast.makeText(requireContext(), "Hello to your profile", Toast.LENGTH_LONG).show()
            } else {
                // Kullanıcı giriş yapmamış, yönlendirme yapabilirsiniz
                findNavController().navigate(R.id.youShouldLoginFragment)
            }
        })
    }



}