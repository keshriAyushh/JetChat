package com.ayush.jetchat.domain.use_case

import com.ayush.digi_inventory.domain.use_case.auth.ForgotPasswordUseCase
import com.ayush.digi_inventory.domain.use_case.auth.SignInUseCase
import com.ayush.digi_inventory.domain.use_case.auth.SignOutUseCase
import com.ayush.digi_inventory.domain.use_case.auth.SignUpUseCase

data class AuthUseCase(
    val singInUseCase: SignInUseCase,
    val singUpUseCase: SignUpUseCase,
    val forgotPassUseCase: ForgotPasswordUseCase,
    val getCurrentUserIdUseCase: GetCurrentUserIdUseCase,
    val signOutUseCase: SignOutUseCase
)
