//region インポート文
package com.example.financerank.viewmodel

import android.os.CountDownTimer
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.financerank.common.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import java.time.LocalTime

//endregion

enum class Field { AuthCd1, AuthCd2, AuthCd3, AuthCd4, AuthCd5, AuthCd6 }
/**
 * メール認証状態監視変数
 */
data class EmailAuthState(

    /** email */
    val email: String = "",

    /** 認証コードフォーカス */
//    val focus: FocusRequester = Field.AuthCd1,

    /** 認証コード1桁目 */
    val authCd1: String = "",

    /** 認証コード2桁目 */
    val authCd2: String = "",

    /** 認証コード3桁目 */
    val authCd3: String = "",

    /** 認証コード4桁目 */
    val authCd4: String = "",

    /** 認証コード5桁目 */
    val authCd5: String = "",

    /** 認証コード6桁目 */
    val authCd6: String = "",

    /** 確認して続行ボタン活性・非活性 */
    val confirmButtonEnabled: Boolean = true,

    /** 再送可能時間 */
    val retryTime: Int = 59,

    /** 再送するボタン活性・非活性 */
    val reSendButtonEnabled: Boolean = false,

    val reSendText: String = "再送する("
)

class EmailAuthVmFactory(
    private val repo: AuthRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmailAuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return EmailAuthViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

/**
 * メール認証専用ViewModel
 */
class EmailAuthViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {


    /**
     * 内部状態を可変化
     */
    private val _state = MutableStateFlow(EmailAuthState())

    /**
     * 外部値渡し用(読み取り専用)
     */
    val state: StateFlow<EmailAuthState> = _state

    /**
     * フォーカスポジション変更
     */
    fun onFocusChange(field: Field) {
//        _state.update { it.copy(focus = field) }
    }

    /**
     * 認証コード1桁目変更
     */
    fun onAuthCd1Change(newValue: String) {
        _state.update { it.copy(authCd1 = newValue) }
    }

    /**
     * 認証コード2桁目変更
     */
    fun onAuthCd2Change(newValue: String) {
        _state.update { it.copy(authCd2 = newValue) }
    }

    /**
     * 認証コード3桁目変更
     */
    fun onAuthCd3Change(newValue: String) {
        _state.update { it.copy(authCd3 = newValue) }
    }

    /**
     * 認証コード4桁目変更
     */
    fun onAuthCd4Change(newValue: String) {
        _state.update { it.copy(authCd4 = newValue) }
    }

    /**
     * 認証コード5桁目変更
     */
    fun onAuthCd5Change(newValue: String) {
        _state.update { it.copy(authCd5 = newValue) }
    }

    /**
     * 認証コード6桁目変更
     */
    fun onAuthCd6Change(newValue: String) {
        _state.update { it.copy(authCd6 = newValue) }
    }

    /**
     * 確認して続行するボタン活性・非活性変更
     */
    fun onConfirmButtonEnabledChange(enabled: Boolean) {
        _state.update { it.copy(confirmButtonEnabled = enabled) }
        if (!enabled) {
            reSendTimeCount()
        } else {
            initReSendTime(59)
            onReSendButtonEnabledChange(false)
        }
    }

    /**
     * 再送するボタン活性・非活性変更
     */
    fun onReSendButtonEnabledChange(enabled: Boolean) {
        _state.update { it.copy(reSendButtonEnabled = enabled) }
    }

    /**
     * 再送可能時間初期化
     */
    fun initReSendTime(initTime: Int) {
        _state.update { it.copy(retryTime = initTime) }
    }

    /**
     * 再送時間カウント更新
     */
    fun reSendTimeCount() {
        var totalTime = 59
        viewModelScope.launch {
            while(totalTime != 0) {
                _state.update { it.copy(retryTime = --totalTime) }
                delay(1000)

                if (totalTime == 1) {
                    onReSendButtonEnabledChange(true)
                }
            }
        }
    }

    /**
     * 再送ボタンテキスト変更
     */
    fun reSendTextChange(newValue: String) {
        _state.update { it.copy(reSendText = newValue) }
    }
}

