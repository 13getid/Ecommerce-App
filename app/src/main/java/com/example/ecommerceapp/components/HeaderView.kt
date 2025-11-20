package com.example.ecommerceapp.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import java.lang.Exception

@Composable
fun  HeaderView(modifier:Modifier = Modifier){

    var  name by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        val  user = FirebaseAuth.getInstance().currentUser ?:run {
            Log.e("Signup","User not logged in")
            name = "Guest"
            return@LaunchedEffect
        }
        try {
            val snapshot = Firebase.firestore.collection("users")
                .document(user.uid)
                .get()
                .await()// This waits for the result

            name = snapshot.getString("name")
                ?.takeIf { it.isNotBlank() }
                ?.split(" ")
                ?.firstOrNull()
                ?:"Friend"
        }catch (e: Exception){
            Log.e("FireStore","Error loading user name:&{e.message}")
            name = "Friend"
        }

    }
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column{
            Text(text = "Welcome Back")
            Text(text = name, style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ))
        }
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        }


}

}