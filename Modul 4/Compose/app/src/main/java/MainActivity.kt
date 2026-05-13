package com.example.list

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.list.presentation.navigation.AppNavigation
import com.example.list.ui.theme.ListTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListTheme {
                AppNavigation()
            }
        }
    }
}