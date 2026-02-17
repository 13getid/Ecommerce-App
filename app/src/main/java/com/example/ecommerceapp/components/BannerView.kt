package com.example.ecommerceapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun BannerView(modifier: Modifier = Modifier){
    var  bannerList by remember {
        mutableStateOf<List<String>>(emptyList())
    }
    LaunchedEffect(Unit) {
        Firebase.firestore.collection("data")
            .document("banners")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val  doc = task.result
                    if (doc != null && doc.exists()){
                        val urls = doc.get("urls")
                        if (urls is List<*>) {
                            bannerList = urls.filterIsInstance<String>()
                        }
                    }
                }
            }
    }

    if (bannerList.isNotEmpty()){
        ImageSlider(modifier = modifier, imageList = bannerList)
    }
    Column(
        modifier = Modifier
    ) {
        val pagerState = rememberPagerState(0) {
            bannerList.size
        }
        HorizontalPager( state = pagerState){
            AsyncImage(
                model = bannerList[it],
                contentDescription = "Banner image",
            )
        }

    }
}
