package com.example.ecommerceapp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfilePage(modifier: Modifier){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(64.dp)
    ) { }
    Text(text = "Profile page")
}