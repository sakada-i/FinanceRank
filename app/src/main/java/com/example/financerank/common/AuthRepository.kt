package com.example.financerank.common

import android.content.Context

interface AuthRepository {

    suspend fun signIn(email: String, password: String, isSignedIn: Boolean): String
}