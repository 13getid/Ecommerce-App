package com.example.ecommerceapp.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

@Composable
fun  HeaderView(modifier:Modifier = Modifier){

    var  name by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        // if no user is logged in,stop the code
        if (user == null){
            Log.e("Signup","user not logged in")
            return@LaunchedEffect
        }
        Firebase.firestore.collection("users")
            .document(user.uid)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val doc = task.result

                    if (doc!= null&& doc.exists()){
                        val fullName = doc.getString("email")?:" "

                        //safely extract first name
                        val firstName = fullName.split(" ")
                            .firstOrNull()
                            ?.trim()
                            ?:" "

                        name = firstName
                    }else{
                        Log.e("FireStore","Document does not exist")
                    }
                }else{
                    Log.e("FireStore","Error: ${task.exception?.message}")
                }
            }
    }

    Column (modifier = Modifier.padding(top = 50.dp)){
        Text(text = "Welcome Back")
        Text(text = name)
    }

}