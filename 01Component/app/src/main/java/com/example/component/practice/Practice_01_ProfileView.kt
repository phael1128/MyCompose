package com.example.component.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileView() {
    Card(
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            GlideImage(
                model = "https://www.nintendo.com/kr/character/kirby/assets/img/intro/kirby-star2.png",
                contentDescription = "프로필 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
                    .align(Alignment.CenterVertically)
                    .border( // ImageView border에 stroke 디자인 해보기
                        width = 2.dp,
                        color = Color.Gray,
                        shape = CircleShape
                    ),

            )
            Spacer(modifier = Modifier.size(8.dp))
            Column(
                verticalArrangement = Arrangement.SpaceEvenly // Child Compose를 균등하게
            ) {
                Text(
                    text = "Dalinaum",
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "엔텔로프 캐년은 죽기 전에 꼭 봐야할 절경으로 소개되었습니다.",
                    fontSize = 10.sp,
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileViewPreview() {
    ProfileView()
}