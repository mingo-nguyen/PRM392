package com.example.loginapp.interfaces

import com.example.loginapp.models.LoginRequest
import com.example.loginapp.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("api/authenticate")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}
