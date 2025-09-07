package com.example.financerank.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.financerank.common.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

//region
/**
 * 新規ユーザー画面状態監視変数
 */
data class RegisterState(

    /** ユーザー名 */
    val userName: String = "",
    /** ユーザー名入力フィールド表示メッセージ */
    val userNameFieldIsMessage: String = "",

    /** メールアドレス */
    val email: String = "",
    /** メールアドレス入力フィールド表示メッセージ */
    val emailFieldIsMessage: String = "",

    /** パスワード */
    val password: String = "",
    /** パスワード入力フィールド表示メッセージ */
    val passwordFieldIsMessage: String = "",

    /** パスワード再 */
    val rePassword: String = "",
    /** パスワード再入力フィールド表示メッセージ */
    val rePasswordFieldIsMessage: String = "",

    /** 招待コード */
    val invitationCd: String = "",

    /** 同意チェック */
    val isConsent: Boolean = false
)
//endregion

//region Factory
class RegisterVmFactory(
    private val repo: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RegisterViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
//endregion

/**
 * 新規ユーザー登録画面専用ViewModel
 */
class RegisterViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    /**
     * 内部状態を可変化
     */
    private val _state = MutableStateFlow(RegisterState())

    /**
     * 外部値渡し用
     */
    var state: StateFlow<RegisterState> = _state

    /**
     * ユーザー名状態変更
     */
    fun onUserNameChange(input: String) {
        // 空ならデフォルト値を入れる
//        val newValue = input.ifEmpty { "山田太郎" }
        _state.update { it.copy(userName = input) }
    }

    /**
     * メールアドレス状態変更
     */
    fun onMailAddressChange(input: String) {
//        val newValue = input.ifEmpty { "you@example.com" }
        _state.update { it.copy(email = input) }
    }

    /**
     * メールアドレスメッセージ状態変更
     */
    fun onUserNameFieldMessageChange(messageValue: String) {
        _state.update { it.copy(userNameFieldIsMessage = messageValue) }
    }

    /**
     * パスワード状態変更
     */
    fun onPasswordChange(input: String) {
//        val newValue = input.ifEmpty { "8文字以上/英数字混在" }
        _state.update { it.copy(password = input) }
    }

    /**
     * パスワードテキストフィールドメッセージ状態変更
     */
    fun onPasswordFieldMessageChange(input: String) {
        _state.update { it.copy(passwordFieldIsMessage = input) }
    }

    /**
     * 再パスワード状態変更
     */
    fun onRePasswordChange(input: String) {
//        val newValue = input.ifEmpty { "もう一度入力" }
        _state.update { it.copy(rePassword = input) }
    }

    /**
     * (任意)招待コード状態変更
     */
    fun onInvitationCd(input: String) {
        _state.update { it.copy(invitationCd = input) }
    }

    /**
     * 同意チェック変更
     */
    fun onConsentChange(checked: Boolean) {
        _state.update { it.copy(isConsent = checked) }
    }
}