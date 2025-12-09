package com.example.ecommerceapp.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
                        if (urls is List<*>){
                            bannerList = urls.filterIsInstance<String>()
                        }else{
                            println("urls is not a list")
                        }
                    }else{
                        println("Document does not exist")
                    }
                }else{
                    println("Error: ${task.exception?.message}")
                }
            }
    }
    }

