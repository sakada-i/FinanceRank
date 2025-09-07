package com.example.financerank

import com.example.financerank.common.AuthRepository
import com.example.financerank.common.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth

object ServiceLocator {
    val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    val authRepository: AuthRepository by lazy { AuthRepositoryImpl(firebaseAuth) }
}