//region インポート文
package com.example.financerank

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.financerank.view.ui.screens.SCR001_SignIn
import com.example.financerank.view.ui.screens.SCR002_Register
import com.example.financerank.view.ui.screens.SCR003_PasswordReset
import com.example.financerank.common.Constants
import com.example.financerank.view.ui.screens.SCR004_EmailAuth

//endregion

/**
 * 遷移先画面ルート
 */
object Routes {
    /** ログイン画面 */
    const val SIGN_IN = "Scr001_SignIn"

    /** ユーザー登録画面 */
    const val REGISTER = "Scr002_Register"

    /** パスワードリセット画面 */
    const val PASSWORD_RESET = "Scr003_PasswordReset"

    /** テスト */
    const val Home = "home"
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        // 初期画面の設定
        startDestination = Constants.Routes.SCR001_SIGN_IN
    ) {
        // Scr001_SignIn(ログイン画面)
        composable(route = Constants.Routes.SCR001_SIGN_IN) {
            SCR001_SignIn(navController = navController)
        }

        // Scr002_Register(ユーザー登録画面)
        composable(route = Constants.Routes.SCR002_REGISTER) {
            SCR002_Register(navController = navController)
        }

        // Scr003_PasswordReset(パスワードリセット画面)
        composable(route = Constants.Routes.SCR003_PASSWORD_RESET) {
            SCR003_PasswordReset(navController = navController)
        }

        // Scr004_EmailAuth(メール認証画面)
        composable(route = Constants.Routes.SCR004_EMAIL_AUTH) {
            SCR004_EmailAuth(navController = navController)
        }
    }
}