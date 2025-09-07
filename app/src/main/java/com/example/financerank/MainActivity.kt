//region import文
package com.example.financerank

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.financerank.view.ui.header.Header
import com.example.financerank.view.ui.theme.FinanceRankTheme
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.auth
//endregion

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FinanceRankTheme {

                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Header()

                        AppNavigation(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun FirebaseTest() {
    lateinit var firebaseAnalytics: FirebaseAnalytics
    val firebaseAuth = remember { Firebase.auth }

    val email = "admin000@gmail.com"
    val password = "admin000"
    FinanceRankTheme {

        Column(
            modifier = Modifier
                .fillMaxSize(), // 画面全体を使う
            verticalArrangement = Arrangement.Center, // 縦方向の中央寄せ
            horizontalAlignment = Alignment.CenterHorizontally // 横方向の中央寄せ
        ) {

            // アカウント作成時
            Button(
                onClick = {
                    firebaseAuth.createUserWithEmailAndPassword("testuser001@gmail.com", "testuser001")
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // 成功処理
                                Log.d(TAG, "新規登録成功")
                            }
                        }
                        .addOnFailureListener {

                        }
                },
                colors = ButtonDefaults.buttonColors()
            ) {
                Text("CREATEBUTTON")
            }


            // ログイン時
            Button(
                onClick = {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // 成功処理
                                Log.d(TAG, "ログイン成功")
                            }
                        }
                        .addOnFailureListener {
                            Log.d(TAG, "ログイン失敗")
                        }
                },
                colors = ButtonDefaults.buttonColors()
            ) {
                Text("LOGINBUTTON")
            }

            // ログアウト時
            Button(
                onClick = {
                    firebaseAuth.signOut()
                },
                colors = ButtonDefaults.buttonColors()
            ) {
                Text("LOGOUTBUTTON")
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FinanceRankTheme {
        Greeting("Android")
    }
}