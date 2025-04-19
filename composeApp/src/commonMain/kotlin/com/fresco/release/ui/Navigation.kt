
package com.fresco.release.ui

import androidx.compose.runtime.*
import com.fresco.release.Release

sealed class Screen {
    data object Home : Screen()
    data class ReleaseDetail(val release: Release) : Screen()
}

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }

    when (val screen = currentScreen) {
        is Screen.Home -> HomeScreen(onReleaseClick = {
            currentScreen = Screen.ReleaseDetail(it)
        })
        is Screen.ReleaseDetail -> ReleaseDetailScreen(
            release = screen.release,
            onBack = { currentScreen = Screen.Home }
        )
    }
}
