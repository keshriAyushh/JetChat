package com.ayush.jetchat.presentation.screens.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ayush.jetchat.R
import com.ayush.jetchat.presentation.ui.theme.Navy

@Composable
fun DetailsScreen() {

    val ctx = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Navy)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Image(
            painter = painterResource(id = R.drawable.user),
            contentDescription = "user_pfp"
        )

    }
}