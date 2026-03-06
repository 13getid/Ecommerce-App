package com.example.ecommerceapp.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.components.CategoryItem
import com.example.ecommerceapp.model.CategoryModel
import com.example.ecommerceapp.model.ProductModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlin.collections.emptyList

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
            .get()
            .addOnSuccessListener { querySnapshot ->
                productList = querySnapshot.documents.mapNotNull { doc ->
                    doc.toObject(ProductModel::class.java)
                }
            }
    }
}

