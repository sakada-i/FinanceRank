//region　インポート文
package com.example.financerank.view.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.financerank.ServiceLocator
import com.example.financerank.common.Constants
import com.example.financerank.viewmodel.PasswordResetViewModel
import com.example.financerank.viewmodel.PasswordResetVmFactory
//endregion

//region 定数
/**
 * メールアドレステキストフィールド
 */
object PassResetMailAddress {
    val constants = Constants.MailAddressTextFieldConst(
        title = "メールアドレス",
        placeholder = "xxxxx@gmail.com",
        message = "※受信できるアドレスをご入力ください"
    )
}
//endregion

//region
@Composable
fun SCR003_PasswordReset(
    navController: NavHostController,
    vmFactory: ViewModelProvider.Factory = PasswordResetVmFactory(ServiceLocator.authRepository)
) {
    val passwordResetViewModel: PasswordResetViewModel = viewModel(factory = vmFactory)
    val state by passwordResetViewModel.state.collectAsStateWithLifecycle()

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
            Text(
                text = "パスワードをお忘れですか？",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 15.dp)
            )

            Text(
                text = "登録済みのメールアドレスを入力してください。\nパスワード再設定用リンクを送信します。",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = 10.dp)
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
                onValueChange = passwordResetViewModel::onEmailChange,
                isError = state.emailError != null,
                supportingText = {
                    if (state.emailError != null) {
                        Text("メールの形式が正しくありません")
                    } else {
                        Text(PassResetMailAddress.constants.message)
                    }
                },
                leadingIcon = { Icon(Icons.Filled.Mail, contentDescription = null) },
                shape = RoundedCornerShape(20.dp),
                placeholder = { Text(text = PassResetMailAddress.constants.placeholder) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.5f),
                ) {
                    Button(
                        onClick = {
                            navController.navigate(Constants.Routes.SCR004_EMAIL_AUTH)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(),
                    ) {
                        Text(
                            text = "リセットリンクを送信",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }

                }
                Row(
                    modifier = Modifier.fillMaxWidth(1f),
                ) {
                    OutlinedButton(
                        onClick = {
                            navController.navigate(Constants.Routes.SCR001_SIGN_IN)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(15.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondary),
                    ) {
                        Text(
                            text = "ログインに戻る",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth(0.30f)
                )
                Text(
                    text = "メールが届かない場合",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 0.dp, start = 6.dp, end = 6.dp, bottom = 0.dp)
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth(1f)
                )
            }

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .wrapContentSize(align = Alignment.Center),
                shape = RoundedCornerShape(size = 15.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "・迷惑メールフォルダをご確認ください。",
                        fontSize = 12.sp,
                    )
                    Text(
                        text = "・「@google.com」ドメインを受信許可に追加してください。",
                        fontSize = 12.sp,
                    )
                    Text(
                        text = "・数分持っても届かない場合は、再送をお試しください。",
                        fontSize = 12.sp,
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
                Text(
                    text = "再送する",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth(0.4f)
                )
                Text(
                    text = "別の方法",
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
                Text(
                    text = "SMSコードで再設定",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }

            OutlinedButton(
                onClick = { },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(15.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondary),
            ) {
                Text(
                    text = "新規登録",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row() {
                    Text(
                        text = "利用規約",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 6.dp, end = 0.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {

                            }
                    )
                    Text(
                        text = "・",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                    Text(
                        text = "プライバシー",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 0.dp, end = 6.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {

                            }
                    )

                }

                Spacer(modifier = Modifier.weight(1f))

                Row() {
                    Text(
                        text = "サポート:",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                    Text(
                        text = "xxxxx@gmail.com",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 0.dp, end = 6.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            ) {

                            }
                    )
                }
            }
        }
    }
}

