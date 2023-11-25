package com.ayush.jetchat.domain.repository

import com.ayush.jetchat.commons.Response
import com.ayush.jetchat.data.model.User
import kotlinx.coroutines.flow.Flow


interface AuthRepository {

    fun getCurrentUserId(): String
    fun signIn(email: String, password: String): Flow<Response<Boolean>>
    fun signUp(user: User, password: String): Flow<Response<Boolean>>
    fun forgotPassword(email: String): Flow<Response<Boolean>>
    fun signOut(): Flow<Response<Boolean>>
}