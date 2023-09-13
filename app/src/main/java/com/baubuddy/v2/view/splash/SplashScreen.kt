package com.baubuddy.v2.view.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.baubuddy.v2.R
import com.baubuddy.v2.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 0.9f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(8f)
                        .getInterpolation(it)
                }
            ))
        delay(200L)
        navController.navigate(AppScreens.HomeScreen.name){
            //Clear the backstack history
            popUpTo(AppScreens.SplashScreen.name) {
                inclusive = true
            }
        }
    })
    
    Surface(modifier = Modifier
        .padding(6.dp)
        .size(348.dp)
        .scale(scale.value),
        shape = RoundedCornerShape(10),
        color = Color.White,
        border = BorderStroke(width = 2.dp, color =  Color.Black)
    )
    {
        Column(modifier = Modifier.padding(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            Image(painter = painterResource(R.drawable.ic_build),
                contentScale = ContentScale.Fit,
                contentDescription = stringResource(R.string.splash),
                modifier = Modifier.size(160.dp))
            Text(text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium)
        }
    }
}