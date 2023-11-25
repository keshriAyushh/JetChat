package com.ayush.jetchat.di

import com.ayush.digi_inventory.domain.use_case.auth.ForgotPasswordUseCase
import com.ayush.digi_inventory.domain.use_case.auth.SignInUseCase
import com.ayush.digi_inventory.domain.use_case.auth.SignOutUseCase
import com.ayush.digi_inventory.domain.use_case.auth.SignUpUseCase
import com.ayush.jetchat.data.repository.AuthRepositoryImpl
import com.ayush.jetchat.domain.use_case.AuthUseCase
import com.ayush.jetchat.domain.use_case.GetCurrentUserIdUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesFirebaseStorage() = FirebaseStorage.getInstance()


    @Provides
    @Singleton
    fun providesAuthRepository(
        auth: FirebaseAuth,
        db: FirebaseFirestore,
        storage: FirebaseStorage
    ) = AuthRepositoryImpl(db = db, storage = storage, auth = auth)

    @Provides
    @Singleton
    fun providesAuthUseCase(
        authRepositoryImpl: AuthRepositoryImpl
    ) = AuthUseCase(
        signOutUseCase = SignOutUseCase(authRepository = authRepositoryImpl),
        singInUseCase = SignInUseCase(authRepository = authRepositoryImpl),
        singUpUseCase = SignUpUseCase(authRepository = authRepositoryImpl),
        getCurrentUserIdUseCase = GetCurrentUserIdUseCase(authRepository = authRepositoryImpl),
        forgotPassUseCase = ForgotPasswordUseCase(authRepository = authRepositoryImpl)
    )

}