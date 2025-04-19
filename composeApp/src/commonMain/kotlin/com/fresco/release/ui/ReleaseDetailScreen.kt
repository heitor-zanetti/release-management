
package com.fresco.release.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fresco.release.Release

@Composable
fun ReleaseDetailScreen(release: Release, onBack: () -> Unit) {
    Column(modifier = Modifier.padding(24.dp)) {
        Button(onClick = onBack) {
            Text("← Back")
        }
        Spacer(Modifier.height(16.dp))
        Text("Release Version ${release.version}", style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("Date: ${release.date}")
        Spacer(Modifier.height(4.dp))
        Text("Status: ${release.status}")
        Spacer(Modifier.height(12.dp))
        Text("Notes:${release.notes}")
    }
}
