package com.example.financerank.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.financerank.viewmodel.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuthState(
    /**
     * ログイン状態保持
     */
    val isSignedIn: Boolean = false,
)

//region Factory
class AuthFactory(
    private val repo: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress()
            return AuthViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
//endregion

/**
 * AWS認証専用ViewModel
 */
class AuthViewModel (
    private val authRepository: AuthRepository
) : ViewModel() {

    /**
     *
     */
    private val _ui = MutableStateFlow(SignInState())

    /**
     * 外部値渡し用(読み取り専用)
     */
    val ui: StateFlow<SignInState> = _ui

    /**
     * ログイン処理
     */
    fun SignIn() {
        // 画面側のログイン状態取得
        val loginUiState = _ui.value

        if (loginUiState.emailError != null || loginUiState.password.isNotEmpty()) {
            // ui更新実施
            _ui.update { it.copy(errorMessage = "入力内容を確認してください") }
            return
        }
        // 非同期スレッドを立てる
        viewModelScope.launch {
            _ui.update { it.copy(isLoading = true, errorMessage = null) }
            runCatching {
                authRepository.signIn(
                    loginUiState.email,
                    loginUiState.password,
                    loginUiState.isSignedIn
                )
            }
                .onSuccess {
                    _ui.update { it.copy(isLoading = false) }
                }
                .onFailure { e ->
                    _ui.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = e.message ?: "ログインに失敗しました"
                        )
                    }
                }
        }
    }
}