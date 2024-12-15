package com.example.cardiosurgeryillustrator.ui.components.patient.community.forum

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cardiosurgeryillustrator.R
import com.example.cardiosurgeryillustrator.ui.theme.Blue100
import com.example.cardiosurgeryillustrator.ui.theme.Typography

@Composable
fun LastMessageForum(
    userAvatar: Int,
    message: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = userAvatar),
            contentDescription = "Avatar do Usuário",
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 16.dp,
                        bottomEnd = 16.dp,
                        bottomStart = 4.dp
                    )
                )
                .background(
                    color = Blue100
                )
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = message,
                style = Typography.bodyMedium,
                color = Color.Black,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun LastMessageForumPreview() {
    LastMessageForum(
        userAvatar = R.drawable.avatar_1,
        message = "Lorem ipsum dolor sit amet, consectetur adipiscing elit..."
    )
}
