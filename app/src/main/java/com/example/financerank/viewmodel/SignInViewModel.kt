//region インポート文
package com.example.financerank.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.financerank.common.AuthRepository
import com.example.financerank.view.ui.screens.PasswordValidation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//endregion

//region ログイン画面状態監視変数
/**
 * ログイン画面状態監視変数
 */
data class SignInState(
    /** メールアドレス入力値保持 */
    val email: String = "",

    /** メールテキストボックス入力時のバリデーションチェックエラー状態保持 */
    val emailError: String? = null,

    /** パスワード入力値保持 */
    val password: String = "",

    /** ログイン状態保持 */
    val isSignedIn: Boolean = false,

    /** 処理完了・未完了 */
    val isLoading: Boolean = false,

    /** エラーメッセージ */
    val errorMessage: String? = null,

    /** バリデーションチェック状態(true: 成功, false: 異常) */
    val passwordValid: Boolean = false,

    /** バリデーションチェックメッセージ */
    val passwordValidMessages: List<String> = mutableListOf<String>()
)
//endregion

//region Factory
class SignInVmFactory(
    private val repo: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignInViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignInViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
//endregion

/**
 * ログイン画面専用ViewModel
 */
class SignInViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    /**
     * 内部状態を可変化
     */
    private val _state = MutableStateFlow(SignInState())

    /**
     * 外部値渡し用(読み取り専用)
     */
    val state: StateFlow<SignInState> = _state

    /**
     * メール形式チェック
     */
    fun onEmailChange(newValue: String) {
        _state.update {
            it.copy(
                email = newValue,
                emailError = if (newValue.isNotEmpty() && !Patterns.EMAIL_ADDRESS.matcher(newValue).matches())
                "メールの形式が正しくありません" else null
            )
        }
    }

    /**
     * パスワード状態変更
     */
    fun onPasswordChange(newValue: String) {
        _state.update { it.copy(password = newValue) }
    }

    /**
     * ログイン状態保持チェック変更
     */
    fun onRememberChange(checked: Boolean) {
        _state.update { it.copy(isSignedIn = checked) }
    }

    /**
     * バリデーション状態変更(true: 成功、false: 異常)
     */
    fun validStateChange(errs: List<String>) {
        _state.update {
            it.copy(passwordValid = errs.isEmpty())
        }
    }

    /**
     * バリデーションメッセージ変更
     */
    fun validMessagesChange(errs: List<String>) {
        _state.update {
            it.copy(passwordValidMessages = errs)
        }
    }

    /**
     * ログイン処理
     */
    fun signIn() {
        // 画面側のログイン状態取得
        val loginUiState = _state.value

        if (loginUiState.emailError != null || loginUiState.password.isNotEmpty()) {
            // ui更新実施
            _state.update { it.copy(errorMessage = "入力内容を確認してください") }
            return
        }
        // 非同期スレッドを立てる
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            runCatching {
                authRepository.signIn(
                    loginUiState.email,
                    loginUiState.password,
                    loginUiState.isSignedIn
                )
            }
            .onSuccess {
                _state.update { it.copy(isLoading = false) }
            }
            .onFailure { e ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.message ?: "ログインに失敗しました"
                    )
                }
            }
        }
    }

    /**
     * パスワード入力フィールドバリデーション
     */
    fun validatePassword(password: String) {
        val errs = mutableListOf<String>()

        if (password.isEmpty()) {
            errs += "パスワードは必須です"
        }
        if (password.length > 15) {
            errs += "15文字以内にしてください"
        }

        // ASCIIの ! (0x21)〜 ~(0x7E) のみ許可 → 全角/非ASCII/空白を排除
        if (password.isNotEmpty() && !PasswordValidation.ASCII_NO_SPACE_1_15.matches(password)) {
            if (password.any { it.isWhitespace() }) {
                errs += "空白（半角・全角）は使用できません"
            }
            if (password.any { it.code !in 0x21..0x7E }) {
                errs += "全角文字や非ASCIIは使用できません"
            }
        }

        if (password.isNotEmpty()
            && !PasswordValidation.HAS_UPPER.containsMatchIn(password)) {
            errs += "大文字を1文字以上含めてください"
        }
        if (password.isNotEmpty()
            && !PasswordValidation.HAS_DIGIT.containsMatchIn(password)) {
            errs += "数字を1文字以上含めてください"
        }
        if (password.isNotEmpty()
            && !PasswordValidation.HAS_SYMBOL.containsMatchIn(password)) {
            errs += "記号を1文字以上含めてください"
        }

        validStateChange(errs)
        validMessagesChange(errs)
    }
}




