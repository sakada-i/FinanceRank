//region インポート文
package com.example.financerank.view.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.financerank.ServiceLocator
import com.example.financerank.view.ui.theme.FinanceRankTheme
import com.example.financerank.viewmodel.RegisterViewModel
import com.example.financerank.viewmodel.RegisterVmFactory
import com.example.financerank.common.Constants
//endregion

//region 定数
/**
 * ユーザーネームテキストフィールド
 */
object UserName {
    const val TITLE = "お名前（ニックネーム可）"
    const val PLACEHOLDER = "山田 太郎"
    const val INIT_MESSAGE = "※他ユーザーには公開されません"
}

/**
 * メールアドレステキストフィールド
 */
object RegisterEmail {
    val constants = Constants.MailAddressTextFieldConst(
        title = "メールアドレス",
        placeholder = "xxxxx@gmail.com",
        message = "※認証メールをお送りします"
    )
}

/**
 * パスワードテキストフィールド
 */
object RegisterPassword {
    val constants = Constants.PasswordTextFieldConst(
        title = "パスワード",
        placeholder = "8文字以上 / 英数字混在",
        message = ""
    )
}

/**
 * パスワード(確認)テキストフィールド
 */
object RegisterRePassword {
    val constants = Constants.RePasswordTextFieldConst(
        title = "パスワード(確認)",
        placeholder = "もう一度入力",
        message = ""
    )
}

/**
 * 招待コード(任意)テキストフィールド
 */
object RegisterInvitationCode {
    val constants = Constants.InvitationCodeTextFieldConst(
        title = "招待コード（任意）",
        placeholder = "例：FR-ABCD1234",
        message = ""
    )
}

/**
 * アイコンの利用権
 */
//object Links {
//    val ICONS8 = "https://icons8.com"
//    val GOOGEL = "https://icons8.com/icon/V5cGWnc9R4xj/google"
//    val APPLE = "https://icons8.com/icon/uoRwwh0lz3Jp/apple-inc"
//    val TWITTER = "https://icons8.com/icon/5MQ0gPAYYx7a/twitter-bird"
//    val X = "https://icons8.com/icon/ZNMifeqJbPRv/x"
//    val FACEBOOK = "https://icons8.com/icon/uLWV5A9vXIPu/facebook"
//}
//endregion

//region データクラス
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
fun RegisterPreview() {
    FinanceRankTheme {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
//            RegisterContent(
//                state = RegisterState(
//                    userName = "",
//                    userNameFieldIsMessage = "",
//                    mailAddress = "",
//                    password = "",
//                    rePassword = "",
//                    invitationCd = "",
//                ),
//                onUserNameChange = {},
//                onUserNameFieldMessageChange = {},
//                onMailAddressChange = {},
//                onPasswordChange = {},
//            )
//            Scr002_Register()
        }
    }
}
//endregion

//region
@Composable
fun SCR002_Register(
//    vm: RegisterViewModel,
//    vm: RegisterViewModel = viewModel(factory = RegisterVmFactory(ServiceLocator.authRepository))
    navController: NavHostController,
    vmFactory: ViewModelProvider.Factory = RegisterVmFactory(ServiceLocator.authRepository)
) {
    val registerViewModel: RegisterViewModel = viewModel(factory = vmFactory)
    val state by registerViewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
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
//                Text(
//                    text = "新規登録",
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(top = 15.dp)
//                )

            Text(
                text = "新規登録",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 15.dp)
            )

            Text(
                text = "メール認証を行います。以下の情報を入力してください",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            var name by remember { mutableStateOf("") }

            var textFieldValue by rememberSaveable { mutableStateOf("グループ名を入力") }
            var userFocused by remember { mutableStateOf(false) }

            // **********************************************
            // ユーザー名入力フィールド
            // **********************************************
            OutlinedTextField(
                value = state.userName,
                onValueChange = { it -> registerViewModel.onUserNameChange(it) },
                modifier = Modifier
                    .wrapContentSize()
                    .onFocusChanged {
//                            if (!it.isFocused) {
//                                state.userName.ifEmpty { "グループ名を入力" }
//                            }
//                            if (!it.isFocused || textFieldValue != "グループ名を入力") return@onFocusChanged
//                            textFieldValue = ""
                        userFocused = it.isFocused
                    }
//                        .defaultMinSize(
//                            minWidth = TextFieldDefaults.MinWidth,
//                            minHeight = TextFieldDefaults.MinHeight
//                        )
                    .fillMaxWidth(),
                label = { Text(UserName.TITLE) },
                shape = RoundedCornerShape(20.dp),
                placeholder = { Text(UserName.PLACEHOLDER) },
                isError = textFieldValue.isEmpty(),
                singleLine = true, // 改行入力不可
                supportingText = {
                    Text(UserName.INIT_MESSAGE)
                }
            )
//                if (textFieldValue.isEmpty()) {
//                    Text(
//                        text = "エラー発生中！！",
//                        fontSize = 11.sp,
//                        color = MaterialTheme.colorScheme.error,
//                    )
//                } else {
//                    Text(
//                        text = "※他ユーザーには公開されません",
//                        fontSize = 11.sp,
//                        color = MaterialTheme.colorScheme.secondary,
//                    )
//                }

            // **********************************************
            // メールアドレス入力フィールド
            // **********************************************
            OutlinedTextField(
                value = state.email,
                onValueChange = { it -> registerViewModel.onMailAddressChange(it) },
                modifier = Modifier
                    .wrapContentSize()
                    .onFocusChanged {
//                            if (!it.isFocused) {
//                                textFieldValue = textFieldValue.ifEmpty { MailAddress.INIT_MESSAGE }
//                            }
//                            if (!it.isFocused || textFieldValue != MailAddress.INIT_MESSAGE) return@onFocusChanged
//                            textFieldValue = ""
                    }
//                        .defaultMinSize(
//                            minWidth = TextFieldDefaults.MinWidth,
//                            minHeight = TextFieldDefaults.MinHeight
//                        )
                    .fillMaxWidth(),
                label = { Text(SignInEmail.constants.title) },
                shape = RoundedCornerShape(20.dp),
                placeholder = { Text(SignInEmail.constants.placeholder) },
                isError = textFieldValue.isEmpty(),
                singleLine = true, // 改行入力不可
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                supportingText = {
                    Text(SignInEmail.constants.message)
                }
            )
//                if (textFieldValue.isEmpty()) {
//                    Text(
//                        text = "エラー発生中！！",
//                        fontSize = 11.sp,
//                        color = MaterialTheme.colorScheme.error,
//                    )
//                } else {
//                    Text(
//                        text = MailAddress.INIT_MESSAGE,
//                        fontSize = 11.sp,
//                        color = MaterialTheme.colorScheme.secondary,
//                    )
//                }

            // **********************************************
            // パスワード入力フィールド
            // **********************************************
            OutlinedTextField(
                value = state.password,
                onValueChange = { it -> registerViewModel.onPasswordChange(it) },
                modifier = Modifier
                    .wrapContentSize()
                    .onFocusChanged {
//                            if (!it.isFocused) {
//                                textFieldValue = textFieldValue.ifEmpty { Password.INIT_MESSAGE }
//                            }
//                            if (!it.isFocused || textFieldValue != MailAddress.INIT_MESSAGE) return@onFocusChanged
//                            textFieldValue = ""
                    }
//                        .defaultMinSize(
//                            minWidth = TextFieldDefaults.MinWidth,
//                            minHeight = TextFieldDefaults.MinHeight
//                        )
                    .fillMaxWidth(),
                label = { Text(SignInPassword.constants.title) },
                shape = RoundedCornerShape(20.dp),
                placeholder = { Text(SignInPassword.constants.placeholder) },
                isError = textFieldValue.isEmpty(),
                singleLine = true // 改行入力不可
            )

            LinearProgressIndicator(
                progress = { 1f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp),
            )

            if (textFieldValue.isEmpty()) {
                Text(
                    text = "エラー発生中！！",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.error,
                )
            } else {
                Text(
                    text = SignInPassword.constants.message,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }



            // パスワードテキストフィールド
//                OutlinedTextField(
//                    value = name,
//                    onValueChange = { name = it },
//                    label = { Text(Password.TITLE) },
//                    shape = RoundedCornerShape(20.dp),
//                    placeholder = { Text(Password.PLACEHOLDER) },
//                    modifier = Modifier.fillMaxWidth()
//                )

            // **********************************************
            // パスワード(再入力)テキストフィールド
            // **********************************************
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(RegisterRePassword.constants.title) },
                shape = RoundedCornerShape(20.dp),
                placeholder = { Text(RegisterRePassword.constants.placeholder) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true, // 改行入力不可
                supportingText = {
                    Text("")
                }
            )

            // **********************************************
            // 招待コードテキストフィールド
            // **********************************************
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(RegisterInvitationCode.constants.title) },
                shape = RoundedCornerShape(20.dp),
                placeholder = { Text(RegisterInvitationCode.constants.placeholder) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true, // 改行入力不可
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = state.isConsent,
                    onCheckedChange = registerViewModel::onConsentChange,
                )
                Text(
                    text = "利用規約",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 3.dp, end = 3.dp)
                        .clickable {}
                )
                Text(
                    text = "と",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    text = "プライバシーポリシー",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 3.dp, end = 3.dp)
                        .clickable {}
                )
                Text(
                    text = "に同意します。",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }

            Row {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        text = "登録する",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
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

            Row {
                Text(
                    text = "すでにアカウントをお持ちですか？",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    text = "ログイン",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 3.dp, end = 3.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                            navController.navigate(Constants.Routes.SCR001_SIGN_IN)
                        }
                )
            }
        }
    }
}