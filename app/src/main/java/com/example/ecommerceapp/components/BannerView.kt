package com.example.ecommerceapp.components

import android.R
import android.util.Log
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

@Composable
fun BannerView(modifier: Modifier = Modifier){

    var  bannerList by remember {
        mutableStateOf<List<String>>(emptyList())
    }
    var isLoading  by remember { mutableStateOf(true) }
    var  errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val  snapshot = Firebase.firestore.collection("data")
                .document("banner")
                .get()
                .await()

            val  urls = snapshot.get("urls")

            bannerList = when(urls){
                is List<*> -> urls.filterIsInstance<String>()
                else -> {
                    Log.w("BannerView","URls field is missing or not a list")
                    emptyList()
                }
            }
    }catch (e: Exception){
        Log.e("BannerView","Failed to load banners: &{e.message} ")
            errorMessage = "Failed to load banners"
            bannerList = emptyList()
    }finally {
        isLoading = false
    }
    }

        }