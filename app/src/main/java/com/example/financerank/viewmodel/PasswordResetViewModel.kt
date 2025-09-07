//region インポート文
package com.example.financerank.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.financerank.common.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

//endregion

//region パスワードリセット画面状態監視変数
/**
 * パスワードリセット画面状態監視変数
 */
data class PasswordResetState(
    /** メールアドレス入力値保持 */
    val email: String = "",

    /** メールテキストボックス入力時のバリデーションチェックエラー状態保持 */
    val emailError: String? = null,
)
//endregion

//region Factory
class PasswordResetVmFactory(
    private val repo: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PasswordResetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PasswordResetViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
//endregion

/**
 * パスワードリセット画面専用ViewModel
 */
class PasswordResetViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    /**
     * 内部状態を可変化
     */
    private val _state = MutableStateFlow(PasswordResetState())
    /**
     * 外部値渡し用
     */
    var state: StateFlow<PasswordResetState> = _state

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
}

