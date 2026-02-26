package com.example.ecommerceapp.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun BannerView(modifier: Modifier = Modifier) {
    //state 1.The list of image urls
    var bannerList by remember {
        mutableStateOf<List<String>>(emptyList())
    }
    //state 2. Are we still loading
    var isLoading by remember {
        mutableStateOf(true)
    }
    //staten 3. Error message
    var hasError by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        Firebase.firestore.collection("data")
            .document("banners")
            .get()
            .addOnCompleteListener { task ->
                isLoading = false // always stop loading when done

                if (task.isSuccessful) {

                    val doc = task.result
                    if (doc != null && doc.exists()) {
                        val urls = doc.get("urls")
                        if (urls is List<*>) {
                            bannerList = urls.filterIsInstance<String>()
                        }
                    } else {
                        Log.e("BannerView", "No document!")
                        hasError = true
                    }
                } else {
                    //firebase call failed
                    Log.e("BannerView", "Error: ${task.exception?.message}")
                    hasError = true
                }
            }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        when {
            //show spinner while loading
            isLoading -> {
                CircularProgressIndicator()
            }
            //show error message
            hasError -> {
                Text(
                    text = "Error loading banners",
                    color = Color.Gray
                )
            }
            //show slider if we have images
            bannerList.isNotEmpty() -> {
                ImageSlider(
                    modifier = Modifier.fillMaxWidth(),
                    imageList = bannerList
                )
            }
            // show message if no images found
            else -> {
                Text(
                    text = "No banners found",
                    color = Color.Gray
                )
            }
        }
    }
}
