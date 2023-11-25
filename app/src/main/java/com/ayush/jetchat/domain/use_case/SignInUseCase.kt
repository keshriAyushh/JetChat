package com.ayush.digi_inventory.domain.use_case.auth

import com.ayush.jetchat.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        email: String, password: String
    ) = authRepository.signIn(email = email, password = password)
}