package com.army.btsmusic.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.army.btsmusic.model.Member
import com.army.btsmusic.model.Track
import com.army.btsmusic.theme.BorahaeNeon
import com.army.btsmusic.theme.BorahaePurple
import com.army.btsmusic.theme.DarkCard
import com.army.btsmusic.theme.DarkOnSurfaceVariant

@Composable
fun TrackListItem(
    track: Track,
    isCurrentTrack: Boolean,
    isPlaying: Boolean,
    downloadProgress: Float?,
    onTrackClick: () -> Unit,
    onDownloadClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val memberColor = Color(track.member.signatureColorHex)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .background(if (isCurrentTrack) DarkCard else Color.Transparent)
            .clickable { onTrackClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Thumbnail Album Cover Art
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color(0xFF1E1035)),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = track.coverUrl,
                contentDescription = track.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(52.dp)
            )

            if (isCurrentTrack && isPlaying) {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color.Black.copy(alpha = 0.45f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.MusicNote,
                        contentDescription = "Playing",
                        tint = BorahaeNeon,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Title, Member Badge & Album
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = track.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = if (isCurrentTrack) FontWeight.Bold else FontWeight.Medium,
                color = if (isCurrentTrack) BorahaeNeon else Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Member Tag Pill
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(memberColor.copy(alpha = 0.25f))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = track.member.stageName,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = memberColor
                    )
                }

                Text(
                    text = "• ${track.album}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = DarkOnSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Duration / Download Button
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = track.durationFormatted,
                style = MaterialTheme.typography.labelSmall,
                color = DarkOnSurfaceVariant
            )

            IconButton(onClick = onDownloadClick) {
                when {
                    downloadProgress != null -> {
                        CircularProgressIndicator(
                            progress = { downloadProgress },
                            modifier = Modifier.size(20.dp),
                            color = BorahaePurple,
                            strokeWidth = 2.dp
                        )
                    }
                    track.isDownloaded -> {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Downloaded Offline",
                            tint = BorahaeNeon,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    else -> {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = "Download Offline",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
