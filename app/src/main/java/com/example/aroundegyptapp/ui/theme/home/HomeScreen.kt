import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.aroundegyptapp.data.model.Data
import com.example.aroundegyptapp.ui.theme.home.HomeViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val postViewModel: HomeViewModel = hiltViewModel()
    ExperienceScreen(postViewModel, navController)
}

@Composable
fun ExperienceScreen(postViewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val recommended by postViewModel.recommended.collectAsState()
    val recent by postViewModel.recent.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SearchBar()
        WelcomeMessage()
        Text(
            "Recommended Experiences",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        if (recommended.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        } else {
            RecommendedList(recommended, navController)
        }

        if (recent.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        } else {
            RecentList(recent)
        }
    }
}

@Composable
fun RecommendedList(posts: List<Data>, navController: NavController) {
    LazyRow(modifier = Modifier.padding(start = 10.dp)) {
        items(posts.size) { post ->
            RecommendedListItem(posts[post], navController)
        }
    }
}

@Composable
fun RecentList(recent: List<com.example.aroundegyptapp.data.model.recent.Data>) {
    LazyColumn(modifier = Modifier.padding(start = 10.dp)) {
        items(recent.size) { post ->
            MostRecentListItem(recent[post])
        }
    }
}

@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Try \"Luxor\"") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
        singleLine = true
    )
}

@Composable
fun WelcomeMessage() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Welcome!", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(
            "Now you can explore any experience in 360 degrees and get all the details about it in one place.",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}

@Composable
fun RecommendedListItem(post: Data, navController: NavController) {
    Column(modifier = Modifier.padding(horizontal = 5.dp)) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .width(320.dp)
                .height(150.dp)
                .padding(vertical = 8.dp)
                .clickable {
                    navController.navigate("details/${post.id}")
                }
        ) {
            Column {

                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(post.views_no.toString(), fontSize = 12.sp, color = Color.Gray)

                    }
                }
            }
        }
        Row() {
            Text(
                post.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 5.dp, top = 15.dp)
            )
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
    }
}

@Composable
fun MostRecentListItem(recent: com.example.aroundegyptapp.data.model.recent.Data) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(vertical = 8.dp)
        ) {
            Column {

                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(recent.views_no.toString(), fontSize = 12.sp, color = Color.Gray)

                    }
                }
            }
        }
        Row() {
            Text(
                recent.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 5.dp, top = 15.dp)
            )
            IconButton(onClick = {}) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "Like",
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewExperienceScreen() {
//    ExperienceScreen()
//}
