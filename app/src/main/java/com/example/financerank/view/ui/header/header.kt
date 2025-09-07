package com.example.financerank.view.ui.header

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financerank.view.ui.theme.FinanceRankTheme

//region 定数
const val APP_NAME = "FinanceRank"
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
fun HeaderPreview() {
    FinanceRankTheme {
        Surface {
            Header()
        }
    }
}
//endregion

@Composable
fun Header() {

    Column(
        modifier = Modifier
            .height(100.dp)
//            .padding(bottom = 10.dp),
    ) {
        Spacer(modifier = Modifier.height(49.dp))
        Row(
            modifier = Modifier.padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                fontSize = 24.sp,
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 15.dp,
                ),
                text = APP_NAME,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 15.dp,
                )
            ) {
//                Image(
//                    painterResource(id = R.drawable.icons8_google_48),
//                    contentDescription = "",
//                    contentScale = ContentScale.None,
//                    modifier = Modifier
//                        .size(40.dp)
//                        .border(
//                            width = 1.dp,
//                            color = MaterialTheme.colorScheme.primaryContainer,
//                            shape = RoundedCornerShape(20.dp)
//                        )
//                        .clip(CircleShape)
//                        .clickable(
//                            onClick = {},
//                        )
//                )
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Googleアイコン",
                    modifier = Modifier
                        .size(40.dp)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .clickable ( onClick = {} )
                )
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth(1f),
            color = MaterialTheme.colorScheme.primary,
        )
    }
}