
package com.fresco.release.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fresco.release.Release

@Composable
fun HomeScreen(onReleaseClick: (Release) -> Unit) {
    var selectedPlatform by remember { mutableStateOf("iOS") }
    val platforms = listOf("iOS", "Android", "Web")

    val releases = mapOf(
        "iOS" to listOf(
            Release("3.5.0", "Apr 17, 2025", "Released", "Bug fixes and UI improvements"),
            Release("3.4.2", "Mar 12, 2025", "Released", "Minor bug fixes"),
        ),
        "Android" to listOf(
            Release("5.1.0", "Apr 15, 2025", "In Progress", "New onboarding flow"),
            Release("5.0.3", "Mar 28, 2025", "Released", "Security patches"),
        ),
        "Web" to listOf(
            Release("2.8.0", "Apr 10, 2025", "Released", "Redesigned dashboard"),
            Release("2.7.1", "Mar 5, 2025", "Released", "Performance improvements"),
        )
    )

    Column(modifier = Modifier.padding(24.dp)) {
        Text("Software Release Management", style = MaterialTheme.typography.h4, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(24.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            platforms.forEach { platform ->
                val isSelected = platform == selectedPlatform
                OutlinedButton(
                    onClick = { selectedPlatform = platform },
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = if (isSelected) MaterialTheme.colors.primary else Color.Transparent,
                        contentColor = if (isSelected) Color.White else MaterialTheme.colors.primary
                    )
                ) {
                    Text(platform)
                }
            }
        }
        Spacer(Modifier.height(24.dp))
        Text("Recent Releases – $selectedPlatform", style = MaterialTheme.typography.h6)
        Spacer(Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            (releases[selectedPlatform] ?: emptyList()).forEach { release ->
                ReleaseCard(release = release, onClick = { onReleaseClick(release) })
            }
        }
    }
}

@Composable
fun ReleaseCard(release: Release, onClick: () -> Unit) {
    Card(
        elevation = 6.dp,
        backgroundColor = Color(0xFFF9FAFB),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Version ${release.version}", fontWeight = FontWeight.Bold)
                Text(release.date, fontStyle = FontStyle.Italic)
            }
            Divider(Modifier.padding(vertical = 8.dp))
            Text(release.status, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(4.dp))
            Text(release.notes)
        }
    }
}
