package com.example.ecommerceapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R

@Composable
fun LoginScreen(modifier: Modifier= Modifier){

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome Back!",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Please enter your details.",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 30.sp

            )
        )
        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = R.drawable.banner1),
            contentDescription = "Banner2",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email address")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Enter your password")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Text(text = "Login", fontSize = 22.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Don't have account?Signup",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )

            )

    }
}