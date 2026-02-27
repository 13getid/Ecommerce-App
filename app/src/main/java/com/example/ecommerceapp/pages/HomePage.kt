package com.example.ecommerceapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.components.BannerView
import com.example.ecommerceapp.components.CategoriesView
import com.example.ecommerceapp.components.HeaderView

@Composable
fun  HomePage(modifier: Modifier){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(64.dp)
    ) {
        HeaderView(modifier)
        Spacer(modifier = Modifier.height(10.dp))
        BannerView(modifier = Modifier.height(150.dp))
        Text( "Categories",
            modifier = Modifier
                .padding(12.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            )
        CategoriesView(modifier = Modifier)

    }
}