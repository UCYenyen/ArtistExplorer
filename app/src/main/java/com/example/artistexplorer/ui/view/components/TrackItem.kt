package com.example.artistexplorer.ui.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.artistexplorer.ui.model.TrackItem

@Composable
fun TrackItem(
    modifier: Modifier = Modifier,
    trackItem: TrackItem,
    trackNumber: Number
) {
    val trackBoxBg = Color(0xFF463E28)
    val trackBoxText = Color(0xFFD8C74B)
    val titleColor = Color(0xFFE0E0E0)
    val durationColor = Color(0xFFA0A0A0)
    val dividerColor = Color(0xFF3A3A3A)

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(trackBoxBg, shape = RoundedCornerShape(4.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = trackNumber.toString(),
                    color = trackBoxText,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.width(16.dp))

            Text(
                text = trackItem.title,
                modifier = Modifier.weight(1f),
                color = titleColor,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )

            Text(
                text = trackItem.duration,
                color = durationColor,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.End
            )
        }

        HorizontalDivider(
            thickness = 1.dp,
            color = dividerColor
        )
    }
}
