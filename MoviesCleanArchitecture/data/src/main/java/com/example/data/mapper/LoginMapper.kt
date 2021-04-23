package com.example.data.mapper

import com.example.data.response.LoginResponse
import com.example.model.login.Login

fun LoginResponse.toDomain() = Login(
    parameter = parameter
)