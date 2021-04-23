package com.example.domain.usecase

import com.example.domain.repository.LoginRepository
import com.example.model.login.Login
import com.example.model.login.User
import com.example.util.domain.Failure
import com.example.util.domain.ResultType

class GetUserUseCase (private val loginRepository: LoginRepository){
    suspend fun invoke(user: User): ResultType<Login, Failure>{
        return loginRepository.getUser(user)
    }
}