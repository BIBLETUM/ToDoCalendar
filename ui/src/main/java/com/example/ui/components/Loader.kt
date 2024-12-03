package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ui.R
import theme.ToDoCalendarTheme

@Composable
internal fun LoaderScreen(
    text: String = stringResource(R.string.loading)
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Loader()
        Text(
            modifier = Modifier.padding(top = 16.dp),
            text = text,
            color = ToDoCalendarTheme.colors.primary,
        )
    }
}

@Composable
private fun Loader() {

    val composition by rememberLottieComposition(
        LottieCompositionSpec
            .RawRes(R.raw.loading_animation)
    )

    val logoAnimationState = animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    com.airbnb.lottie.compose.LottieAnimation(
        composition = composition,
        progress = { logoAnimationState.progress }
    )

}