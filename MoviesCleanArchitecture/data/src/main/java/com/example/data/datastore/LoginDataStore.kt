package com.example.data.datastore

import com.example.model.login.Login
import com.example.model.login.User
import com.example.util.domain.Failure
import com.example.util.domain.ResultType

interface LoginDataStore {
    suspend fun getUser(user : User) : ResultType<Login, Failure>
}