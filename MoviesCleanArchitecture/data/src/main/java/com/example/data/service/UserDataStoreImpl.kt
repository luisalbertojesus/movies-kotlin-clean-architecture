package com.example.data.service

import com.example.data.datastore.LoginDataStore
import com.example.data.mapper.toDomain
import com.example.data.response.LoginResponse
import com.example.model.login.Login
import com.example.model.login.User
import com.example.util.domain.Failure
import com.example.util.domain.ResultType

class UserDataStoreImpl : LoginDataStore{
    override suspend fun getUser(user: User): ResultType<Login, Failure> {
        return try {
            var userApi = LoginResponse(
                "1",
                "0"
            )
            if(userApi.state == "0"){
                ResultType.Success(
                    userApi.toDomain()
                )
            }else{
                ResultType.Error(
                    Failure.NetworkConnection
                )
            }
        } catch (t: Throwable) {
            ResultType.Error(
                Failure.NetworkConnection
            )
        }
    }
}