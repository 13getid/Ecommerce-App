package com.example.ecommerceapp.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.ecommerceapp.model.CategoryModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun CategoriesView (modifier: Modifier = Modifier){

    var categoryList by remember {
        mutableStateOf<List<CategoryModel>>(emptyList())
    }

    val isLoading = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        Firebase.firestore
            .collection("categories")
            .get()
            .addOnCompleteListener { snapshot ->
                categoryList.value = snapshot.documents.mapNotNull{ doc ->
                    try {
                        CategoryModel(
                            id = doc.id,
                            name = doc.getString("name") ?:" "
                        )
                    }
                }
            }
    }

    }
