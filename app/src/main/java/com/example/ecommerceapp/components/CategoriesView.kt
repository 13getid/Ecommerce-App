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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.ui.text.font.FontWeight
import com.example.ecommerceapp.GlobalNavigation

@Composable
fun CategoriesView (modifier: Modifier = Modifier){

    var categoryList by remember {
        mutableStateOf<List<CategoryModel>>(emptyList())
    }

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        Log.d("CATEGORY","Fetching categories")
        Firebase.firestore
            .collection("data")
            .document("stock")
            .collection("categories")
            .get()
            .addOnSuccessListener { querySnapshot ->
                categoryList = querySnapshot.documents.mapNotNull{ doc ->
                    try {
                        CategoryModel(
                            id = doc.id,
                            name = doc.getString("name") ?:"",
                            imageUrl = doc.getString("imageUrl") ?:""
                        )
                    }catch (e: Exception){
                        Log.e("CATEGORY", "Error parsing doc: ${e.message}")
                        null
                    }
                }
                isLoading = false
                Log.d("CATEGORY","Loaded ${categoryList.size}categories")
            }
            .addOnFailureListener { e ->
                Log.e("CATEGORY", "❌ Error: ${e.message}")
                        isLoading = false
            }
    }
    if (isLoading){
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
            items(categoryList){ category ->
                CategoryItem(
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
fun CategoryItem(
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
            modifier = Modifier.size(80.dp)
                .clickable{
                   GlobalNavigation.navController.navigate("category-products/"+category.id)
                },
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            AsyncImage(
                model = category.imageUrl,
                contentDescription = category.name,
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = category.name,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(top = 4.dp)
                .widthIn(max = 80.dp)
        )
    }
}