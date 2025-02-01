package com.example.aroundegyptapp.ui.theme.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.app.com.example.aroundegyptapp.data.model.recommended.Data
import com.example.aroundegyptapp.R

@Composable
fun HomeScreen(navController: NavController) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    ExperienceScreen(homeViewModel, navController)
}

@Composable
fun ExperienceScreen(homeViewModel: HomeViewModel = hiltViewModel(), navController: NavController) {
    val recommended by homeViewModel.recommended.collectAsState()
    val recent by homeViewModel.recent.collectAsState()
    val results by homeViewModel.searchResult.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        SearchBar(
            viewModel = homeViewModel,
            searchQuery = searchQuery,
            onSearchQueryChange = { newQuery ->
                searchQuery = newQuery
            }
        )

        if (searchQuery.isNotEmpty()) {

            if (results.isNotEmpty()) {
                SearchResultsList(title = "Search Results", experiences = results, navController)
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No results found", color = Color.Gray)
                }
            }
        } else {
            WelcomeMessage()
            RecommendedExperienceList(
                title = "Recommended Experiences",
                experiences = recommended,
                navController
            )
            RecentExperienceList(title = "Most Recent", experiences = recent, navController)
        }
    }
}

@Composable
fun SearchResultsList(
    title: String,
    experiences: List<Data>,
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        if (experiences.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(modifier = Modifier.padding(start = 10.dp)) {
                items(experiences.size) { index ->
                    RecommendedListItem(experiences[index], navController)
                }
            }
        }
    }
}

@Composable
fun RecommendedExperienceList(
    title: String,
    experiences: List<Data>,
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        if (experiences.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyRow(modifier = Modifier.padding(start = 10.dp)) {
                items(experiences.size) { index ->
                    RecommendedListItem(experiences[index], navController)
                }
            }
        }
    }
}

@Composable
fun RecentExperienceList(
    title: String,
    experiences: List<Data>,
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        if (experiences.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(modifier = Modifier.padding(start = 10.dp)) {
                items(experiences.size) { index ->
                    RecommendedListItem(experiences[index], navController)
                }
            }
        }
    }
}


@Composable
fun SearchBar(
    viewModel: HomeViewModel,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit
) {
    var isSearching by remember { mutableStateOf(false) }

    TextField(
        value = searchQuery,
        onValueChange = { newQuery ->
            onSearchQueryChange(newQuery)
            isSearching = newQuery.isNotEmpty()
            if (newQuery.isEmpty()) {
                viewModel.clearSearch()
            }
        },
        placeholder = { Text("Try \"Luxor\"") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 40.dp),
        shape = RoundedCornerShape(8.dp),
        leadingIcon = {
            IconButton(onClick = {
                if (searchQuery.isNotEmpty()) {
                    viewModel.executeSearch(searchQuery)
                }
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = {
                    onSearchQueryChange("")
                    viewModel.clearSearch()
                    isSearching = false
                }) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear Search")
                }
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                if (searchQuery.isNotEmpty()) {
                    viewModel.executeSearch(searchQuery)
                }
            }
        )
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
fun RecommendedListItem(
    post: Data, navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val likesMap by viewModel.likesMap.collectAsState()

    var isLiked by remember { mutableStateOf(false) }
    var likesCount by remember { mutableIntStateOf(post.likes_no) }

    LaunchedEffect(likesMap[post.id]) {
        likesMap[post.id]?.let { updatedLikes ->
            likesCount = updatedLikes
            isLiked = true
        }
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate("details/${post.id}")
        }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp, top = 16.dp, bottom = 16.dp)
        ) {
            AsyncImage(
                model = post.cover_photo,
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.view_icon),
                    contentDescription = "Views",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
                Text(
                    text = post.views_no.toString(),
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.gallery_icon),
                    contentDescription = "Views",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.info_icon),
                    contentDescription = "Views",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.view_dimension_icon),
                    contentDescription = "Views",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = post.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (!isLiked) {
                            viewModel.likeExperience(post.id)
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
    }
}

@Composable
fun MostRecentListItem(
    recent: com.example.aroundegyptapp.data.model.recent.Data, navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val likesMap by viewModel.likesMap.collectAsState()

    var isLiked by remember { mutableStateOf(false) }
    var likesCount by remember { mutableIntStateOf(recent.likes_no) }

    LaunchedEffect(likesMap[recent.id]) {
        likesMap[recent.id]?.let { updatedLikes ->
            likesCount = updatedLikes
            isLiked = true
        }
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            navController.navigate("details/${recent.id}")
        }) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = recent.cover_photo,
                contentDescription = "Background Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.view_icon),
                    contentDescription = "Views",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
                Text(
                    text = recent.views_no.toString(),
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.gallery_icon),
                    contentDescription = "Views",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.info_icon),
                    contentDescription = "Views",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.view_dimension_icon),
                    contentDescription = "Views",
                    modifier = Modifier.size(20.dp),
                    tint = Color.White
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = recent.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.weight(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (!isLiked) {
                            viewModel.likeExperience(recent.id)
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
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewExperienceScreen() {
    ExperienceScreen(
        homeViewModel = hiltViewModel(),
        navController = NavController(LocalContext.current)
    )
}
