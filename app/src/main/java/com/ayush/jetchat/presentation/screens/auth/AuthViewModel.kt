package com.ayush.jetchat.presentation.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.jetchat.commons.Response
import com.ayush.jetchat.data.model.User
import com.ayush.jetchat.domain.use_case.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _signInState = MutableStateFlow<Response<Boolean>>(Response.Success(false))
    val signInState = _signInState

    private val _signUpState = MutableStateFlow<Response<Boolean>>(Response.Success(false))
    val signUpState = _signUpState

    private val _signOutState = MutableStateFlow<Response<Boolean>>(Response.Success(false))
    val signOutState = _signOutState

    private val _forgotPassState = MutableStateFlow<Response<Boolean>>(Response.Success(false))
    val forgotPassState = _forgotPassState


    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authUseCase.singInUseCase(email = email, password = password)
                .collect {
                    _signInState.value = it
                }
        }

    }

    fun signUp(user: User, password: String) {
        viewModelScope.launch {
            authUseCase.singUpUseCase(user = user, password = password)
                .collect { value ->
                    _signUpState.value = value
                }
        }
    }

    fun getCurrentUserId() = authUseCase.getCurrentUserIdUseCase()

    fun signOut() {
        viewModelScope.launch {
            authUseCase.signOutUseCase()
                .collect {
                    _signOutState.value = it
                }
        }
    }

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            authUseCase.forgotPassUseCase(email = email)
                .collect {
                    _forgotPassState.value = it
                }
        }
    }
}