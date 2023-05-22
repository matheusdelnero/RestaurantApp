package com.example.restaurantapp.ui.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.restaurantapp.MainActivity
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentLoginBinding
import com.example.restaurantapp.db.MealDataBase
import com.example.restaurantapp.db.UserDataBase
import com.example.restaurantapp.model.User
import com.example.restaurantapp.ui.Home.HomeViewModel
import kotlinx.coroutines.runBlocking


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: HomeViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.singUp.setOnClickListener {
            it.background = resources.getDrawable(R.drawable.switch_trcks,null)
            binding.singUp.setTextColor(resources.getColor(R.color.white,null))
            binding.logIn.background = null
            binding.singUpLayout.visibility = View.VISIBLE
            binding.endereco.visibility = View.VISIBLE
            binding.bairro.visibility = View.VISIBLE
            binding.logInLayout.visibility = View.GONE
            binding.logIn.setTextColor(resources.getColor(R.color.strongOrange,null))
            binding.ou.textSize = 12F
        }
        binding.logIn.setOnClickListener {
            binding.singUp.background = null
            binding.singUp.setTextColor(resources.getColor(R.color.strongOrange,null))
            binding.logIn.background = resources.getDrawable(R.drawable.switch_trcks,null)
            binding.singUpLayout.visibility = View.GONE
            binding.logInLayout.visibility = View.VISIBLE
            binding.logIn.setTextColor(resources.getColor(R.color.white,null))
            binding.ou.textSize = 18F
        }

        binding.singIn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_myAccountFragment)
            val telefone: String = binding.telefone.text.toString()
            val endereco: String = binding.endereco.text.toString()
            val bairro: String = binding.bairro.text.toString()
            val senha: String = binding.senha.text.toString()
            val confirmsenha : String = binding.confirmsenha.text.toString()


            val user = User(telefone,endereco,bairro,senha,confirmsenha)

            val userDataBase by lazy {
                Room.databaseBuilder(
                    requireContext(),
                    UserDataBase::class.java,
                    "user.db"
                ).build()
            }

            runBlocking {
                userDataBase.userDao().insertUser(user)
            }

        }




    }

}