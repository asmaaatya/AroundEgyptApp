package com.example.aroundegyptapp.ui.theme.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.aroundegyptapp.R
import com.example.aroundegyptapp.data.model.details.DetailsData

@Composable
fun DetailsScreen(navController: NavController, itemId: String?, detailsViewModel: DetailsViewModel = hiltViewModel()) {
    LaunchedEffect(itemId) {
        detailsViewModel.fetchDetails(itemId!!)
    }
    val details by detailsViewModel.details.collectAsState()

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            AsyncImage(
                model = details?.cover_photo,
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )


            Button(
                onClick = { /* TODO: Handle click */ },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Text("EXPLORE NOW", color = Color.Black, fontWeight = FontWeight.Bold)
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.view_icon),
                    contentDescription = "Views",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
                Text(
                    text = details?.views_no.toString(),
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.gallery_icon),
                    contentDescription = "Views",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )

            }
        }

        details?.let { ExperienceDetails(it,detailsViewModel) }
    }
}

@Composable
fun ExperienceDetails(data: DetailsData,detailsViewModel: DetailsViewModel = hiltViewModel()) {
    val likesMap by detailsViewModel.likesMap.collectAsState()
    var isLiked by remember { mutableStateOf(false) }
    var likesCount by remember { mutableIntStateOf(data.likes_no) }
    LaunchedEffect(likesMap[data.id]) {
        likesMap[data.id]?.let { updatedLikes ->
            likesCount = updatedLikes
            isLiked = true
        }
    }
    Column(modifier = Modifier.padding(start = 16.dp, top = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(data.title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            IconButton(
                onClick = {
                    if (!isLiked) {
                        detailsViewModel.likeExperience(data.id)
                    }
                }
            ) {
                Icon(
                    imageVector = if (isLiked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = "Like",
                    tint = if (isLiked) Color.Red else Color.Black,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }

            Text(
                text = likesCount.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(data.gmap_location.type, fontSize = 16.sp, color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            data.description,
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }

