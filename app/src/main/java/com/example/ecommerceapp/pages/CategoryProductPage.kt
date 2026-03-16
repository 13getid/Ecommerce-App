package com.example.ecommerceapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.components.ProductItem
import com.example.ecommerceapp.model.ProductModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun CategoryProductPage(modifier: Modifier = Modifier, categoryId: String) {

    var productList by remember {
        mutableStateOf<List<ProductModel>>(emptyList())
    }

    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf("") }

    LaunchedEffect(categoryId) {
        Log.d("PRODUCTS", "Fetching products for category:$categoryId")

        isLoading = true
        errorMessage = ""

        Firebase.firestore
            .collection("data")
            .document("stock")
            .collection("products")
            .whereEqualTo("categoryId", categoryId)
            .get()
            .addOnSuccessListener { querySnapshot ->
                productList = querySnapshot.documents.mapNotNull { doc ->
                    try {
                        ProductModel(
                            id = doc.id,
                            title = doc.getString("title") ?: "",
                            actualPrice = doc.getDouble("actualPrice") ?: 0.0,
                            price = doc.getDouble("price") ?: 0.0,
                            images = doc.getString("images") ?: "",
                            category = doc.getString("category"),
                            categoryId = doc.getString("category"),
                            description = doc.getString("description") ?: ""
                        )
                    } catch (e: Exception) {
                        Log.e("PRODUCTS", "Error parsing product:${e.message}")
                        null
                    }
                }
                isLoading = false
                Log.d("PRODUCTS", "Fetched ${productList.size} products for $categoryId")
            }
            .addOnFailureListener { e ->
                Log.e("PRODUCTS", "Error fetching products: ${e.message}")
                errorMessage = e.message ?: ""
                isLoading = false
            }
    }

    Column(modifier = modifier.fillMaxSize()) {
        Text(
            text = "Products",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            errorMessage.isNotEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error: $errorMessage",
                        color = Color.Red
                    )
                }
            }
            productList.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "No products found in this category")
                }
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(productList) { product ->
                        ProductItem(product = product)

                         }
                }
            }
        }
    }
}
