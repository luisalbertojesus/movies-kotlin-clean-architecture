package com.example.moviescleanarchitecture.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.data.repository.LoginRepositoryImpl
import com.example.domain.usecase.GetUserUseCase
import com.example.model.login.User
import com.example.moviescleanarchitecture.R
import com.example.util.presentation.getViewModel
import com.example.util.presentation.validateNetwork

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var goMoviesButton : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViewModel()
        initWidgets()
        initObserver()

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_login, container, false)

        setup(view)

        return view
    }

    private fun setup(view: View){
        goMoviesButton = view.findViewById(R.id.goMoviesButton)
    }

    private fun initWidgets(){
        goMoviesButton.setOnClickListener {
            validateConnection(
                User(
                    "Admin",
                    "Password*123"
                )
            )
        }
    }

    private fun initViewModel(){
        viewModel = requireActivity().getViewModel {
            LoginViewModel(
                GetUserUseCase(
                    LoginRepositoryImpl()
                )
            )
        }
    }

    private fun initObserver() {
        viewModel.loginLive.observe(viewLifecycleOwner, Observer(::onLoginResult))
    }

    private fun onLoginResult(result: LoginResult) {
        when (result) {
            is LoginResult.Success -> {
                Toast.makeText(requireContext(), "SUCCESS", Toast.LENGTH_LONG).show()
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMovieFragment())
            }
            is LoginResult.Error -> {
                Toast.makeText(requireContext(), "ERROR", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateConnection(user: User) {
        if (validateNetwork(requireActivity())) {
            viewModel.login(user)
        } else {
            Toast.makeText(requireContext(), "Error de Conexión de Red", Toast.LENGTH_LONG).show()
        }
    }
}