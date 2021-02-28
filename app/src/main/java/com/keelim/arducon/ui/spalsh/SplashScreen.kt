package com.keelim.arducon.ui.spalsh

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SplashWindow(appName: String){
    Surface(color = MaterialTheme.colors.background){
        Column(modifier = Modifier.padding(32.dp)) {
            Spacer(modifier = Modifier.height(42.dp))
            Text(
                text = appName,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 36.sp,
                    )
                )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "'Welcome to ArduController'",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 12.sp,
                )
            )
        }
    }
}