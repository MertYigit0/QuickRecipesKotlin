package com.mertyigit0.quickrecipeskotlin.LoginRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.mertyigit0.quickrecipeskotlin.R
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentLoginBinding
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentMealDetailBinding
import com.mertyigit0.quickrecipeskotlin.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null;
    private val binding get() = _binding!!;

    private lateinit var auth: FirebaseAuth
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = Firebase.auth
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding =   FragmentRegisterBinding.inflate(inflater,container,false)
        val view = binding.root;
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(requireView())


       binding.registerButton.setOnClickListener{
           val email = binding.editTextEmailAddressRegister.text.toString()
           val password = binding.editTextPassword.text.toString()
           val passwordConfirm = binding.editTextPasswordConfirmRegister.text.toString()
           if (email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
               // Boş alan kontrolü
               Toast.makeText(requireContext(), "Please fill in all fields.", Toast.LENGTH_SHORT).show()
           } else if (password != passwordConfirm) {
               // Şifre eşleşmesi kontrolü
               Toast.makeText(requireContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show()
           } else {
               // Firebase kullanıcı kaydı
               auth.createUserWithEmailAndPassword(email, password)
                   .addOnCompleteListener(requireActivity()) { task ->
                       if (task.isSuccessful) {
                           createOrGetUserDocument()
                           // Kayıt başarılı
                           Toast.makeText(requireContext(), "Registration successful.", Toast.LENGTH_SHORT).show()
                           // İsterseniz burada giriş ekranına yönlendirebilirsiniz.
                           navController.navigate(R.id.action_registerFragment_to_loginFragment)
                       } else {
                           // Kayıt başarısız
                           Toast.makeText(requireContext(), "Registration failed. Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                       }
                   }
           }
       }

    }
    private fun createOrGetUserDocument() {
        // Kullanıcının UID'sini al
        val userId = FirebaseAuth.getInstance().currentUser?.uid

        // Kullanıcı UID'si varsa işleme devam et
        userId?.let {
            // "users" koleksiyonu referansı
            val usersCollection = db.collection("users")

            // Kullanıcının UID'si ile belirtilen bir doküman referansı
            val userDocument = usersCollection.document(it)

            // Doküman var mı kontrol et
            userDocument.get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document = task.result
                    if (document != null && document.exists()) {
                        // Doküman zaten varsa işleme devam et
                        Toast.makeText(activity, "Kullanıcı dokümanı zaten var", Toast.LENGTH_SHORT).show()
                        // Burada favoriler koleksiyonunu ekleyebilirsiniz
                    } else {
                        // Doküman yoksa oluştur
                        userDocument.set(hashMapOf("field1" to "value1", "field2" to "value2"))
                            .addOnSuccessListener {
                                // Doküman başarıyla oluşturuldu
                                Toast.makeText(activity, "Kullanıcı dokümanı oluşturuldu", Toast.LENGTH_SHORT).show()
                                // Burada favoriler koleksiyonunu ekleyebilirsiniz
                            }
                            .addOnFailureListener { e ->
                                // Hata durumunda buraya düşer
                                println("Doküman oluşturulurken hata oluştu: $e")
                            }
                    }
                } else {
                    // Hata durumunda buraya düşer
                    println("Doküman var mı kontrol edilirken hata oluştu: ${task.exception}")
                }
            }
        }
    }




}