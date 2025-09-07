package com.example.financerank.common

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
//region 定数
/**
 * ログイン時表示メッセージ
 */
object Message {
    const val SUCCESS = "成功しました"
    const val FAILURE = "エラーが発生しました"
    const val LOGIN = "ログインしました"
    const val LOGOUT = "ログアウトしました"
    const val NOT_FOUND = "メールアドレスまたはパスワードが正しくありません"
    const val REGIST_FAILURE = "新規登録時に失敗しました"
}
//endregion

//region メンバ変数
var message = ""
//endregion

class AuthRepositoryImpl(
    private val auth: FirebaseAuth,
) : AuthRepository {

    /**
     * ログイン処理
     *
     * @param email メールアドレス
     * @param password パスワード
     */
    override suspend fun signIn(
        email: String,
        password: String,
        isSignedIn: Boolean
    ): String {
        val authResult = auth.signInWithEmailAndPassword(
            email,
            password
        ).await()

        return authResult.user?.uid ?: ""
    }

//    /**
//     * 新規登録処理
//     *
//     * @param mailAddress メールアドレス
//     * @param password パスワード
//     */
//    @Composable
//    fun Regist(
//        mailAddress: String,
//        password: String
//    ) {
//
//        val firebaseAuth = remember { Firebase.auth }
//        firebaseAuth.createUserWithEmailAndPassword(
//            mailAddress,
//            password
//        )
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // 成功処理
//                    Log.d(TAG, "登録成功")
//                    message = Message.LOGIN
//                }
//            }
//            .addOnFailureListener {
//                Log.d(TAG, "登録失敗")
//                message = Message.REGIST_FAILURE
//            }
//    }
//
//    /**
//     * ログアウト処理
//     *
//     * @param
//     */
//    @Composable
//    fun Logout() {
//
//        val firebaseAuth = remember { Firebase.auth }
//        try {
//            firebaseAuth.signOut()
//            Log.d(TAG, "ログアウト成功")
//            message = Message.LOGOUT
//        } catch(e: Exception) {
//            Log.d(TAG, "ログアウト失敗")
//        }
//    }
}

