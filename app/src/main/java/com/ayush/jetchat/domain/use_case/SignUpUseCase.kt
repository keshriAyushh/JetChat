package com.ayush.digi_inventory.domain.use_case.auth

import com.ayush.jetchat.data.model.User
import com.ayush.jetchat.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(user: User, password: String) =
        authRepository.signUp(user = user, password = password)
}