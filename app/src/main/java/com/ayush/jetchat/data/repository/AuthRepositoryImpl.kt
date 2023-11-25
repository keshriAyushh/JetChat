package com.ayush.jetchat.data.repository

import com.ayush.jetchat.commons.Constants.UNKNOWN_ERROR
import com.ayush.jetchat.commons.Constants.USERS_COLLECTION
import com.ayush.jetchat.commons.Response
import com.ayush.jetchat.data.model.User
import com.ayush.jetchat.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val storage: FirebaseStorage,
    private val db: FirebaseFirestore
): AuthRepository {

    private var isSuccess = false

    override fun signIn(email: String, password: String): Flow<Response<Boolean>>  = flow {
        isSuccess = false
        try {
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    isSuccess = true
                }
                .addOnFailureListener {
                    isSuccess = false
                }
                .await()
            emit(Response.Success(isSuccess))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: UNKNOWN_ERROR))
        }
    }

    override fun signOut(): Flow<Response<Boolean>> = flow{
        try {
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: UNKNOWN_ERROR))
        }
    }

    override fun signUp(user: User, password: String): Flow<Response<Boolean>> = flow {
        isSuccess = false
        try {
            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(user.email, password)
                .addOnSuccessListener {
                    isSuccess = true
                }
                .addOnFailureListener {
                    isSuccess = false
                }
                .await()
            if(isSuccess) {
                db.collection(USERS_COLLECTION)
                    .document()
                    .set(user)
            }
            emit(Response.Success(isSuccess))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: UNKNOWN_ERROR))
        }
    }

    override fun getCurrentUserId() = auth.currentUser?.uid.toString()

    override fun forgotPassword(email: String): Flow<Response<Boolean>> = flow {
        isSuccess = false
        try {
            emit(Response.Loading)
            auth.sendPasswordResetEmail(email)
                .addOnSuccessListener {
                    isSuccess = true
                }
                .addOnFailureListener {
                    isSuccess = false
                }
                .await()
            emit(Response.Success(isSuccess))
        } catch (e: Exception) {
            emit(Response.Error(e.message ?: UNKNOWN_ERROR))
        }
    }
}