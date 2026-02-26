package com.example.ecommerceapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImageSlider(imageList: List<String>,modifier: Modifier = Modifier) {
    //Safety check - don't crash if list is empty!
    if (imageList.isEmpty())return

    val  pagerState = rememberPagerState(
        initialPage = 0
    )
    //auto scroll every 3 seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % imageList.size
            pagerState.animateScrollToPage(nextPage) //smooth slide animation
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //the sliding images
        HorizontalPager(
            count = imageList.size,
            state = pagerState,
            modifier = Modifier
                .height(240.dp)
                .fillMaxWidth()
        ) { page ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                .data(imageList[page])
                .crossfade(true)  // enable smooth fade-in
                .build(),
                contentDescription = "Banner ${page + 1}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop  // fills the space perfectly
            )
        }
        //Dots showing the current slide
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(12.dp),
            activeColor = Color.Black,
            inactiveColor = Color.Gray.copy(alpha = 0.5f) // More subtle inactive dots
        )

    }
}