package com.example.authapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import authapp.composeapp.generated.resources.Res
import authapp.composeapp.generated.resources.compose_multiplatform
import authapp.composeapp.generated.resources.icons8_google
import org.jetbrains.compose.resources.painterResource

@Composable
fun GoogleLoginButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(width = 0.dp, color = Color.Gray),
        modifier = Modifier
            .padding(16.dp)
            .height(50.dp)
            .fillMaxWidth(),
        elevation = ButtonDefaults.elevation(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(painter = painterResource(Res.drawable.icons8_google), contentDescription = "Google Logo",modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp))

            Text(
                text = "Sign in with Google",
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}
