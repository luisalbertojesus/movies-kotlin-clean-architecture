package com.example.moviescleanarchitecture.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetUserUseCase
import com.example.model.login.User
import com.example.util.domain.ResultType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel (
    private var getUserUseCase: GetUserUseCase
): ViewModel(){

    private var _loginLive = MutableLiveData<LoginResult>()
    val loginLive: LiveData<LoginResult> get() = _loginLive

    fun login(user: User){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                getUserUseCase.invoke(user)
            }
            when(result){
                is ResultType.Success -> {
                    val state = result.value
                    _loginLive.value = LoginResult.Success(state)
                }
                is ResultType.Error -> {
                    _loginLive.value = LoginResult.Error(result.value)
                }
            }
        }
    }
}