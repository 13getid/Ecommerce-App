package com.example.ecommerceapp.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
                            name = doc.getString("name") ?:"",
                            imageUrl = doc.getString("imageUrl") ?:""
                        )
                    }catch (e: Exception){
                        Log.e("CATEGORY","Error: ${e.message}")
                        null
                    }
                }
                isLoading.value = false
                Log.d("CATEGORY","Loaded ${categoryList.value.size}categories")
            }
            .addOnFailureListener { e ->
                Log.e("CATEGORY", "Error: ${e.message})
                        isLoading.value = false
            }
    }
    if (isLoading.value){
        Box(
            modifier = modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ){
            CircularProgressIndicator()
        }
    }else{
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categoryList.value){ category ->
                categoryItem(
                    category = category,
                    onClick ={
                        Log.d("CATEGORY","Clicked: ${category.name}")
                    }
                )
        }
    }
    }
}
@Composable
fun categoryItem(
    category: CategoryModel,
    onClick:() -> Unit ={}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
    ) {
        Card(
            modifier = Modifier.size(70.dp),
            shape = CircleShape,
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            AsyncImage(
                model = category.imageUrl,
                contentDescription = category.name,
                modifier = Modifier.size(70.dp),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = category.name,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 4.dp)
                .widthIn(max = 80.dp)
        )
    }
}