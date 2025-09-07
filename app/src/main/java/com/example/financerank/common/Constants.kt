package com.example.financerank.common

import androidx.annotation.DrawableRes
import com.example.financerank.R

/**
 * 画面共通定数クラス
 */
object Constants {

    /**
     * 遷移先画面ルート
     */
    object Routes {
        /** Scr001_SignIn(ログイン画面) */
        const val SCR001_SIGN_IN = "Scr001_SignIn"

        /** Scr002_Register(ユーザー登録画面) */
        const val SCR002_REGISTER = "Scr002_Register"

        /** Scr003_PasswordReset(パスワードリセット画面) */
        const val SCR003_PASSWORD_RESET = "Scr003_PasswordReset"

        /** Scr004_EmailAuth(メール認証画面) */
        const val SCR004_EMAIL_AUTH = "Scr004_EmailAuth"

        /** テスト */
        const val Home = "home"
    }

    /**
     * アカウント連携用ボタンアイコン
     */
    object LinkIcons {
        @DrawableRes
        val GOOGLE = R.drawable.icons8_google_48
        @DrawableRes
        val APPLE = R.drawable.icons8_apple_inc_48
        @DrawableRes
        val TWITTER = R.drawable.icons8_twitter_bird_48
        @DrawableRes
        val X = R.drawable.icons8_x_48
        @DrawableRes
        val FACEBOOK = R.drawable.icons8_facebook_48
    }

    /**
     * アイコンの利用権
     */
    object Links {
        val ICONS8 = "https://icons8.com"
        val GOOGEL = "https://icons8.com/icon/V5cGWnc9R4xj/google"
        val APPLE = "https://icons8.com/icon/uoRwwh0lz3Jp/apple-inc"
        val TWITTER = "https://icons8.com/icon/5MQ0gPAYYx7a/twitter-bird"
        val X = "https://icons8.com/icon/ZNMifeqJbPRv/x"
        val FACEBOOK = "https://icons8.com/icon/uLWV5A9vXIPu/facebook"
    }


    /**
     * メールアドレステキストフィールド定数
     */
    class MailAddressTextFieldConst(
        var title: String,
        var placeholder: String,
        var message: String
    )

    /**
     * パスワードテキストフィールド定数
     */
    class PasswordTextFieldConst(
        var title: String,
        var placeholder: String,
        var message: String
    )

    /**
     * パスワード(確認)テキストフィールド定数
     */
    class RePasswordTextFieldConst(
        var title: String,
        var placeholder: String,
        var message: String
    )

    /**
     * 招待コード(任意)テキストフィールド定数
     */
    class InvitationCodeTextFieldConst(
        var title: String,
        var placeholder: String,
        var message: String
    )
}