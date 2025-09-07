//region インポート文
package com.example.financerank.view.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component3
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component4
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component5
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component6
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.financerank.ServiceLocator
import com.example.financerank.common.Constants
import com.example.financerank.viewmodel.EmailAuthViewModel
import com.example.financerank.viewmodel.EmailAuthVmFactory

//endregion

@Composable
fun SCR004_EmailAuth(
    navController: NavHostController,
    vmFactory: ViewModelProvider.Factory = EmailAuthVmFactory(ServiceLocator.authRepository)
) {
    val emailAuthViewModel: EmailAuthViewModel = viewModel(factory = vmFactory)
    val state by emailAuthViewModel.state.collectAsStateWithLifecycle()

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
                text = "メール認証コードを入力",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 15.dp)
            )

            Text(
                text = "送信したら6桁のコードを入力してください。迷惑メールフォルダもご確認ください。",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
//                    .wrapContentSize(align = Alignment.Center)
                shape = RoundedCornerShape(size = 15.dp),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "送信先：",
                        fontSize = 12.sp,
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
                    .focusGroup(),
                horizontalArrangement = Arrangement.spacedBy(8.dp) // 均等な“間”を固定値で
            ) {
                val (a, b, c, _, _, _) = remember { FocusRequester.createRefs() }

                // 認証コード1桁目
                OutlinedTextField(
                    value = state.authCd1,
                    shape = RoundedCornerShape(size = 15.dp),
                    onValueChange = { it ->
                        if (it.length <= 1) { // 1文字以下の場合のみ更新
                            emailAuthViewModel.onAuthCd1Change(it)
                        }
                    },
                    modifier = Modifier
                        .height(63.dp)
                        .width(63.dp)
                        .weight(1f)
                        .focusRequester(a)
                        .focusProperties { next = b },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                        cursorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                // 認証コード2桁目
                OutlinedTextField(
                    value = state.authCd2,
                    shape = RoundedCornerShape(size = 15.dp),
                    onValueChange = { it ->
                        if (it.length <= 1) { // 1文字以下の場合のみ更新
                            emailAuthViewModel.onAuthCd2Change(it)
                        }
                    },
                    modifier = Modifier
                        .height(63.dp)
                        .width(63.dp)
                        .weight(1f)
                        .focusRequester(b)
                        .focusProperties { next = c },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                        cursorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                // 認証コード3桁目
                OutlinedTextField(
                    value = state.authCd3,
                    shape = RoundedCornerShape(size = 15.dp),
                    onValueChange = { it ->
                        if (it.length <= 1) { // 1文字以下の場合のみ更新
                            emailAuthViewModel.onAuthCd3Change(it)
                        }
                    },
                    modifier = Modifier
                        .height(63.dp)
                        .width(63.dp)
                        .weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                        cursorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                // 認証コード4桁目
                OutlinedTextField(
                    value = state.authCd4,
                    shape = RoundedCornerShape(size = 15.dp),
                    onValueChange = { it ->
                        if (it.length <= 1) { // 1文字以下の場合のみ更新
                            emailAuthViewModel.onAuthCd4Change(it)
                        }
                    },
                    modifier = Modifier
                        .height(63.dp)
                        .width(63.dp)
                        .weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                        cursorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                // 認証コード5桁目
                OutlinedTextField(
                    value = state.authCd5,
                    shape = RoundedCornerShape(size = 15.dp),
                    onValueChange = { it ->
                        if (it.length <= 1) { // 1文字以下の場合のみ更新
                            emailAuthViewModel.onAuthCd5Change(it)
                        }
                    },
                    modifier = Modifier
                        .height(63.dp)
                        .width(63.dp)
                        .weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                        cursorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                // 認証コード6桁目
                OutlinedTextField(
                    value = state.authCd6,
                    shape = RoundedCornerShape(size = 15.dp),
                    onValueChange = { it ->
                        if (it.length <= 1) { // 1文字以下の場合のみ更新
                            emailAuthViewModel.onAuthCd6Change(it)
                        }
                    },
                    modifier = Modifier
                        .height(63.dp)
                        .width(63.dp)
                        .weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
                        cursorColor = Color.Transparent
                    ),
                    textStyle = TextStyle(
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
            }


            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { emailAuthViewModel.onConfirmButtonEnabledChange(false) },
                    modifier = Modifier.fillMaxWidth(0.4f),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(),
                    enabled = state.confirmButtonEnabled
                ) {
                    Text(
                        text = "確認して続行",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                }
                Spacer(modifier = Modifier.weight(0.05f))
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(0.9f),
                    shape = RoundedCornerShape(15.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondary),
                ) {
                    Text(
                        text = "クリップボードから貼り付け",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 12.sp
                    )
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
                    modifier = Modifier.fillMaxWidth(0.35f)
                )
                Text(
                    text = "コードが届かない場合",
                    color = MaterialTheme.colorScheme.secondary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 0.dp, start = 6.dp, end = 6.dp, bottom = 0.dp)
                )
                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth(1f)
                )

            }

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(0.5f),
                ) {
                    Button(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(),
                    ) {
                        Text(
                            text = "メールアプリを開く",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary,
                        )
                    }

                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier.fillMaxWidth(0.7f),
                ) {
                    OutlinedButton(
                        onClick = { emailAuthViewModel.onConfirmButtonEnabledChange(true) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(15.dp),
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondary),
                        enabled = state.reSendButtonEnabled
                    ) {
                        if (!state.reSendButtonEnabled) {
                            Text(
                                text = "再送する(${state.retryTime})",
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        } else {
                            if (state.retryTime == 0) {
                                Text(
                                    text = "再送する",
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.secondary,
                                )
                            }
                        }
//                        if (state.retryTime == 0) {
//                            Text(
//                                text = "再送する",
//                                fontWeight = FontWeight.Bold,
//                                color = MaterialTheme.colorScheme.secondary,
//                            )
//                        } else {
//                            Text(
//                                text = "再送する(${state.retryTime})",
//                                fontWeight = FontWeight.Bold,
//                                color = MaterialTheme.colorScheme.secondary,
//                            )
//                        }
                    }
                }
            }


            Row {
                Text(
                    text = "ヘルプ",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 3.dp, end = 3.dp)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                        ) {
                        }
                )
                Spacer(modifier = Modifier.weight(1f))
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