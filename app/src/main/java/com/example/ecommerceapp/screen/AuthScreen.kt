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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ecommerceapp.R

@Composable
fun AuthScreen(modifier: Modifier = Modifier, navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner3),
            contentDescription = "Banner",
            modifier = Modifier.fillMaxWidth()
                .height(350.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Start your shopping journey now",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )

            )
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Best Ecommerce platform with best prices",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )

        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                navController.navigate("login")
            },
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
        ) {
            Text(text = "Login", fontSize = 22.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = {
                navController.navigate("signup")
            },
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)
        ) {
            Text(text = "Signup", fontSize = 22.sp)
        }

    }
}