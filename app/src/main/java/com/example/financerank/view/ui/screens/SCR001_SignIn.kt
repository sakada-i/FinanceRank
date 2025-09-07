//region インポート文
package com.example.financerank.view.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.financerank.ServiceLocator
import com.example.financerank.view.ui.theme.FinanceRankTheme
import com.example.financerank.viewmodel.SignInState
import com.example.financerank.viewmodel.SignInViewModel
import com.example.financerank.viewmodel.SignInVmFactory
import com.example.financerank.common.Constants

//endregion

//region 定数
/**
 * メールアドレステキストフィールド
 */
object SignInEmail {
    val constants = Constants.MailAddressTextFieldConst(
        title = "メールアドレス",
        placeholder = "xxxxx@gmail.com",
        message = "※認証メールをお送りします"
    )
}

/**
 * パスワードテキストフィールド
 */
object SignInPassword {
    val constants = Constants.PasswordTextFieldConst(
        title = "パスワード",
        placeholder = "8文字以上 / 英数字混在",
        message = ""
    )
}

/**
 * バリデーションチェックルール
 */
object PasswordValidation {
    val ASCII_NO_SPACE_1_15 = Regex("^[!-~]{1,15}$")
    val HAS_UPPER = Regex("[A-Z]")
    val HAS_DIGIT = Regex("\\d")
    val HAS_SYMBOL = Regex("[^A-Za-z0-9]")
}
//endregion

//region メンバ変数
//endregion

//region 画面プレビュー
@Preview(
    name = "Light",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Dark",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SignInPreview() {
    FinanceRankTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            var password by rememberSaveable { mutableStateOf("") }

//            Scr001_SignIn()
//            ValidateInput()
//            PasswordValidatingTextField(
//                value = password,
//                onValueChange = { password = it }
//            )
        }
    }
}
//endregion

/**
 * ログイン画面
 */
@Composable
fun SCR001_SignIn(
    navController: NavHostController,
    vmFactory: ViewModelProvider.Factory = SignInVmFactory(ServiceLocator.authRepository)
//    signInViewModel: SignInViewModel = viewModel(
//        factory = SignInVmFactory(ServiceLocator.authRepository)
//    ),
//    authViewModel: AuthViewModel = viewModel(
//        factory = AuthFactory(ServiceLocator.authRepository)
//    ),
//    onSignInSuccess: () -> Unit,
//    onToSignUp: () -> Unit,
) {
    val signInViewModel: SignInViewModel = viewModel(factory = vmFactory)

    // ViewModelが公開しているFlowをComposeのStateに変換する
    val state by signInViewModel.state.collectAsStateWithLifecycle()
//    val authVm by authViewModel.ui.collectAsStateWithLifecycle()
//    val uiState by signInViewModel.SignInUiState.collectAsState()

//    LaunchedEffect(ui.isSginedIn) {
//        if (ui.isSignedIn) {
//            onSignInSuccess()
//        }
//    }

//    LaunchedEffect(authVm.isSignedIn) {
//        if (state.isSignedIn) {
//            onSignInSuccess()
//        }
//    }

    Column(
        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center, // 縦方向の中央寄せ
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(
                    top = 0.dp,
                    start = 15.dp,
                    end = 15.dp,
                    bottom = 0.dp
                )
        ) {
            Text(
                text = "ログイン",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 15.dp)
            )

            Text(
                text = "アカウントにサインインしてください",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "メールアドレス",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            // **********************************************
            // メールアドレス入力フィールド
            // **********************************************
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.email,
                onValueChange = signInViewModel::onEmailChange,
                isError = state.emailError != null,
                supportingText = {
                    if (state.emailError != null) {
                        Text("メールの形式が正しくありません")
                    }
                },
                leadingIcon = { Icon(Icons.Filled.Mail, contentDescription = null) },
                shape = RoundedCornerShape(20.dp),
                placeholder = { Text(text = SignInEmail.constants.placeholder) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "パスワード",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            // **********************************************
            // パスワード入力フィールド
            // **********************************************
            PasswordValidatingTextField(
                value = state.password,
//                    onValueChange = signInViewModel::onPasswordChange
                singInViewModel = signInViewModel,
                state = state
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = state.isSignedIn,
                    onCheckedChange = signInViewModel::onRememberChange,
//                        modifier = Modifier
//                            .scale(0.8f)
//                            .size(20.dp)
                )

                Text(
                    text = "ログイン状態を保持",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "パスワードをお忘れですか?",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                    ) {
                        navController.navigate(Constants.Routes.SCR003_PASSWORD_RESET)
                    }
                )
            }

            Button(
                onClick = { signInViewModel.signIn() },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(),
            ) {
                Text(
                    text = "ログイン",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth(0.45f)
                )
                Text(
                    text = "または",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 0.dp, start = 6.dp, end = 6.dp, bottom = 0.dp)
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth(1f)
                )
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondary),
            ) {
                Box(Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(Constants.LinkIcons.GOOGLE),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(Modifier.width(ButtonDefaults.IconSpacing))
                    Text(
                        text = "Googleで続行",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.align(Alignment.Center)       // 中央
                    )
                }
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondary),
            ) {
                Box(Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(Constants.LinkIcons.APPLE),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(Modifier.width(ButtonDefaults.IconSpacing))
                    Text(
                        text = "Appleで続行",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.align(Alignment.Center)       // 中央
                    )
                }
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondary),
            ) {
                Box(Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(Constants.LinkIcons.X),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(Modifier.width(ButtonDefaults.IconSpacing))
                    Text(
                        text = "X で続行",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.align(Alignment.Center) // 中央寄せ
                    )
                }
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondary),
            ) {
                Box(Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(Constants.LinkIcons.FACEBOOK),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(Modifier.width(ButtonDefaults.IconSpacing))
                    Text(
                        text = "Facebookで続行",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.align(Alignment.Center) // 中央寄せ
                    )
                }
            }

            Column() {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "アカウントをお持ちでないですか?",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                    Text(
                        text = "新規登録",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 6.dp, end = 6.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {
                                navController.navigate(Constants.Routes.SCR002_REGISTER)
                            }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "ログインをもって",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                    Text(
                        text = "利用規約・プライバシー",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 0.dp, end = 0.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {

                            }
                    )
                    Text(
                        text = "に同意したものとみなします。",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }
    }
}

/**
 * パスワード入力フィールド
 */
@Composable
fun PasswordValidatingTextField(
    value: String,
//    onValueChange: (String) -> Unit,
    singInViewModel: SignInViewModel,
    modifier: Modifier = Modifier,
    state: SignInState
) {
    var visible by rememberSaveable { mutableStateOf(false) }
    var touched by rememberSaveable { mutableStateOf(false) }

    // 入力時に「15文字まで」「ASCIIかつスペース無し」にフィルタ（ハードブロック）
    fun filter(input: String): String =
        input.filter { it.code in 0x21..0x7E }   // 全角/空白/非ASCIIを除去
            .take(15)                           // 15文字制限

//    val result = remember(value, touched) {
        if (touched) {
//            validatePassword(value)
            singInViewModel.validatePassword(value)
        } else {
//            PwResult(ok = true, errors = emptyList())
            singInViewModel.validStateChange(emptyList())
            singInViewModel.validMessagesChange(emptyList())
        }
//    }

    OutlinedTextField(
        value = value,
        onValueChange = {
            if (!touched) touched = true
//            onValueChange(filter(it)) },
                singInViewModel.onPasswordChange(filter(it)) },

        modifier = modifier.fillMaxWidth(),
        singleLine = true,
//        isError = touched && !result.ok,
        isError = touched && !state.passwordValid,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (visible) VisualTransformation.None
        else PasswordVisualTransformation('●'),
        leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
        trailingIcon = {
            IconButton(onClick = { visible = !visible }) {
                Icon(
                    if (visible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = if (visible) "パスワードを隠す" else "パスワードを表示"
                )
            }
        },
        supportingText = {
//            if (touched && !result.ok) {
            if (touched && !state.passwordValid) {
                // 先頭エラーだけ表示。全部出したい場合は result.errors.forEach { ... }
//                Text(result.errors.first())
                Text(state.passwordValidMessages.first())
            }
        },
        placeholder = { Text(text = SignInPassword.constants.placeholder) },
        shape = RoundedCornerShape(20.dp),
    )
}
