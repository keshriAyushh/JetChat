package com.ayush.jetchat.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ayush.jetchat.presentation.navigation.RootNavigationGraph
import com.ayush.jetchat.presentation.ui.theme.JetChatTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetChatTheme {
                RootNavigationGraph()
            }
        }
    }
}
