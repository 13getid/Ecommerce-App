package com.example.ecommerceapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
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
fun SignupScreen(modifier: Modifier= Modifier){

    var  email by remember {
        mutableStateOf("")
    }

    var  name by remember {
        mutableStateOf("")
    }

    var  password by remember {
        mutableStateOf("")
    }

    Column(
         modifier = Modifier
             .fillMaxSize()
             .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Hello There!",
             modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )

        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Create an account ",
            modifier = Modifier.fillMaxWidth(),
            style = TextStyle(
                fontSize = 30.sp,
            )

        )
        Spacer(modifier = Modifier.height(10.dp))

        Image(
            painter = painterResource(id = R.drawable.banner1),
            contentDescription = "Banner1",
            modifier = Modifier.fillMaxWidth()
                .height(300.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email,
            onValueChange ={
            email =it
        },
            label = {
                Text(text = "Email address")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name,
            onValueChange ={
                name =it
            },
            label = {
                Text(text = "Full name")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange ={
                password =it
            },
            label = {
                Text(text = "Create your password")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange ={
                password =it
            },
            label = {
                Text(text = "Confirm your password")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
        ) {
            Text(text = "Signup", fontSize = 22.sp)
        }


        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Already have account?Login",
            style = TextStyle(
                  fontSize = 15.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        )
    }

}