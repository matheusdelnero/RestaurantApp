package com.example.restaurantapp.ui.Login

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.restaurantapp.MainActivity
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentLoginBinding
import com.example.restaurantapp.db.UserDataBase
import com.example.restaurantapp.model.User
import com.example.restaurantapp.ui.Home.HomeViewModel
import kotlinx.coroutines.runBlocking


class LoginFragment : Fragment(),View.OnClickListener,View.OnFocusChangeListener,View.OnKeyListener {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: HomeViewModel
    fun validatePhone(): Boolean{
        var errorMessage: String? = null
        val value: String = binding.telefone.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Digite o Telefone"
        } else if(!Patterns.PHONE.matcher(value).matches()){
            errorMessage = "E-Mail invalido."
        }

        if (errorMessage != null){
            binding.telefoneLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }

    fun validateAdress(): Boolean {
        var errorMessage: String? = null
        val value: String = binding.endereco.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Digite o Endereço"
        }
        if (errorMessage != null) {
            binding.enderecoLayout.apply {
                isErrorEnabled = true
                error = errorMessage

            }
        }
        return errorMessage == null
    }




    fun validateBairro(): Boolean {
        var errorMessage: String? = null
        val value: String = binding.bairro.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Digite o Bairro"
        }
        if (errorMessage != null) {
            binding.bairroLayout.apply {
                isErrorEnabled = true
                error = errorMessage

            }
        }
        return errorMessage == null
    }

    fun validatePasswordLogin(): Boolean{
        var errorMessage: String? = null
        val value: String = binding.senhalogin.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Digite a Senha"
        } else if(value.length < 6){
            errorMessage = "A Senha deve conter no minimo 6 Digitos."
        }
        if (errorMessage != null) {
            binding.senhaLayout.apply {
                isErrorEnabled = true
                error = errorMessage

            }
        }

        return errorMessage == null
    }

    fun validatePassword(): Boolean{
        var errorMessage: String? = null
        val value: String = binding.senha.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Digite a Senha"
        } else if(value.length < 6){
            errorMessage = "A Senha deve conter no minimo 6 Digitos."
        }
        if (errorMessage != null) {
            binding.senhaLayout.apply {
                isErrorEnabled = true
                error = errorMessage

            }
        }
        return errorMessage == null
    }

    fun validateConfirmPassword(): Boolean{
        var errorMessage: String? = null
        val value: String = binding.confirmsenha.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Digite a Confirmação de senha"
        } else if(value.length < 6){
            errorMessage = "A Confirmação de Senha deve conter no minimo 6 Digitos."
        }
        if (errorMessage != null) {
            binding.confirmSenhaLayout.apply {
                isErrorEnabled = true
                error = errorMessage

            }
        }
        return errorMessage == null
    }

    fun validatePasswordAndConfirm(): Boolean{
        var errorMessage: String? = null
        val senha = binding.senha.text.toString()
        val confirmSenha = binding.confirmsenha.text.toString()
        if (senha != confirmSenha){
            errorMessage = "Confirmação de Senha não é igual a Senha."
        }
        if (errorMessage != null) {
            binding.confirmSenhaLayout.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity as MainActivity).viewModel












    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.telefone.onFocusChangeListener = this
        binding.endereco.onFocusChangeListener = this
        binding.bairro.onFocusChangeListener = this
        binding.senha.onFocusChangeListener = this
        binding.confirmsenha.onFocusChangeListener = this
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userDataBase by lazy {
            Room.databaseBuilder(
                requireContext(),
                UserDataBase::class.java,
                "user.db"
            ).build()
        }

        fun validatePhoneAlreadyTaken(){
            val email = binding.telefone.text.toString()
            runBlocking {
                val count = userDataBase.userDao().checkIfEmailExists(email)
                if (count > 0) {
                    // O telefone já está cadastrado
                    Toast.makeText(context, "Telefone já cadastrado", Toast.LENGTH_SHORT).show()
                } else {
                    val telefone: String = binding.telefone.text.toString()
                    val endereco: String = binding.endereco.text.toString()
                    val bairro: String = binding.bairro.text.toString()
                    val senha: String = binding.senha.text.toString()
                    val confirmsenha : String = binding.confirmsenha.text.toString()
                    val user = User(telefone,endereco,bairro,senha,confirmsenha)
                    runBlocking { userDataBase.userDao().insertUser(user) }
                    findNavController().navigate(R.id.action_loginFragment_to_myAccountFragment)
                }}


        }

        binding.singUp.setOnClickListener {
            it.background = resources.getDrawable(R.drawable.switch_trcks,null)
            binding.singUp.setTextColor(resources.getColor(R.color.white,null))
            binding.logIn.background = null
            binding.singUpLayout.visibility = View.VISIBLE
            binding.endereco.visibility = View.VISIBLE
            binding.bairro.visibility = View.VISIBLE
            binding.logInLayout.visibility = View.GONE
            binding.logIn.setTextColor(resources.getColor(R.color.strongOrange,null))
            //binding.ou.textSize = 12F
            binding.singIn.visibility = View.GONE
            binding.botaocadastro.visibility = View.VISIBLE
        }
        binding.logIn.setOnClickListener {
            binding.singUp.background = null
            binding.singUp.setTextColor(resources.getColor(R.color.strongOrange,null))
            binding.logIn.background = resources.getDrawable(R.drawable.switch_trcks,null)
            binding.singUpLayout.visibility = View.GONE
            binding.logInLayout.visibility = View.VISIBLE
            binding.logIn.setTextColor(resources.getColor(R.color.white,null))
            binding.botaocadastro.visibility = View.GONE
            binding.singIn.visibility = View.VISIBLE
            //binding.ou.textSize = 18F

        }

        binding.botaocadastro.setOnClickListener {
            val telefone: String = binding.telefone.text.toString()
            val endereco: String = binding.endereco.text.toString()
            val bairro: String = binding.bairro.text.toString()
            val senha: String = binding.senha.text.toString()
            val confirmsenha : String = binding.confirmsenha.text.toString()
            val user = User(telefone,endereco,bairro,senha,confirmsenha)

            runBlocking { if (user.telefone!!.isNotEmpty() && user.endereco!!.isNotEmpty() && user.bairro!!.isNotEmpty() && user.senha!!.isNotEmpty() && user.confirmacaoSenha!!.isNotEmpty()){
                validatePhoneAlreadyTaken()
            } } }



        binding.singIn.setOnClickListener {
            val tel = binding.telefonelogin.text.toString()
            val senha = binding.senhalogin.text.toString()

            runBlocking {
                val user = userDataBase.userDao().getUserByTelefoneESenha(tel, senha)

                if (user != null) {
                    findNavController().navigate(R.id.action_loginFragment_to_myAccountFragment)
                    //Usuario Encontrado
                } else {
                    Toast.makeText(context, "Usuario ou senha incorreto", Toast.LENGTH_SHORT).show()
                    // Usuário não encontrado ou credenciais incorretas
                } }

        }





    }

    override fun onClick(view: View?) {

    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null){
            when(view.id){
                R.id.telefone -> {
                    if(hasFocus){
                        if(binding.telefoneLayout.isErrorEnabled){
                            binding.telefoneLayout.isErrorEnabled = false
                        }
                    } else {if(validatePhone()){
                        //confirmar se ja nao existe conta com email
                    }}
                }


                R.id.endereco -> {
                    if(hasFocus){
                        if(binding.enderecoLayout.isErrorEnabled){
                            binding.enderecoLayout.isErrorEnabled = false
                        }
                    } else {validateAdress()}
                }
                R.id.bairro -> {
                    if(hasFocus){
                        if(binding.bairroLayout.isErrorEnabled){
                            binding.bairroLayout.isErrorEnabled = false
                        }

                    } else {validateBairro()}
                }
                R.id.senha -> {
                    if(hasFocus){
                        if(binding.senhaLayout.isErrorEnabled){
                            binding.senhaLayout.isErrorEnabled = false
                        }
                    } else {if(validatePassword() && binding.confirmsenha.text!!.isNotEmpty() && validateConfirmPassword() && validatePasswordAndConfirm()){
                        if (binding.confirmSenhaLayout.isErrorEnabled){
                            binding.confirmSenhaLayout.isErrorEnabled = false
                        }
                        binding.confirmSenhaLayout.apply {
                            setStartIconDrawable(R.drawable.check_circle_24)
                            setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                        }
                    }}
                }
                R.id.confirmsenha -> {
                    if(hasFocus){
                        if(binding.confirmSenhaLayout.isErrorEnabled){
                            binding.confirmSenhaLayout.isErrorEnabled = false
                        }
                    } else {
                        if (validateConfirmPassword() && validatePassword() && validatePasswordAndConfirm()){

                            if (binding.senhaLayout.isErrorEnabled){
                                binding.senhaLayout.isErrorEnabled = false
                            }
                            binding.confirmSenhaLayout.apply {
                                setStartIconDrawable(R.drawable.check_circle_24)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false
    }

}