package com.ayush.digi_inventory.domain.use_case.auth

import com.ayush.jetchat.domain.repository.AuthRepository
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String) = authRepository.forgotPassword(email = email)
}