package com.example.data.repository

import com.example.data.service.UserDataStoreImpl
import com.example.domain.repository.LoginRepository
import com.example.model.login.Login
import com.example.model.login.User
import com.example.util.domain.Failure
import com.example.util.domain.ResultType

class LoginRepositoryImpl : LoginRepository {
    private val userDataStoreImpl = UserDataStoreImpl()
    override suspend fun getUser(user: User): ResultType<Login, Failure> =
        userDataStoreImpl.getUser(user)
}