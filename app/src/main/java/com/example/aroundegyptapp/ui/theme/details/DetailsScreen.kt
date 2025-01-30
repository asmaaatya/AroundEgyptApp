package com.example.aroundegyptapp.ui.theme.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.aroundegyptapp.R
import com.example.aroundegyptapp.data.model.details.Data

@Composable
//@Preview(showBackground = true)
fun DetailsScreen(navController: NavController, itemId: String?) {
    val detailsViewModel: DetailsViewModel = hiltViewModel()
    detailsViewModel.fetchDetails(itemId!!)
    val details by detailsViewModel.details.collectAsState()
    //navController.popBackStack()
    Column() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with actual image
                contentDescription = "Experience Image",
                modifier = Modifier.fillMaxSize()
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
        }
        details?.let { ExperienceDetails(it) }
    }

}

@Composable
fun ExperienceDetails(data: Data) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(data.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(data.description, fontSize = 16.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(data.views_no.toString(), fontSize = 14.sp, color = Color.Gray)
            IconButton(onClick = { /* TODO: Handle like */ }) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Like")
            }
        }
        Column() {
            Text(data.title, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                data.detailed_description,
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Justify
            )
        }
    }

}
