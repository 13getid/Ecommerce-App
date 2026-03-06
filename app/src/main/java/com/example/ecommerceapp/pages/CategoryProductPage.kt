package com.example.ecommerceapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecommerceapp.model.ProductModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun CategoryProductPage(modifier: Modifier = Modifier,categoryId : String){

    var productList by remember {
        mutableStateOf<List<ProductModel>>(emptyList())
    }

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(categoryId) {
        Log.d("PRODUCTS", "Fetching products for category:$categoryId")

        isLoading = true

        Firebase.firestore
            .collection("data")
            .document("stock")
            .collection("products")
            .whereEqualTo("categoryId",categoryId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                productList = querySnapshot.documents.mapNotNull { doc ->
                    doc.toObject(ProductModel::class.java)
                }
                Log.d("PRODUCTS","Fetched ${productList.size} products")
                isLoading = false
            }
            .addOnFailureListener { e ->
                Log.e("PRODUCTS","Error fetching products", e)
                isLoading = false
            }
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(productList){item ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            ) {
                AsyncImage(
                    model = item.images,
                    contentDescription = item.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(text = item.title)
            }
        }
    }
}

