package com.example.moviescleanarchitecture.login

import com.example.model.login.Login
import com.example.util.domain.Failure

sealed class LoginResult {
    class Success(val value: Login): LoginResult()
    class Error(val failure: Failure): LoginResult()
}