package com.opd.token.tv.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opd.token.shared.designsystem.theme.LightBlueBackground
import com.opd.token.shared.designsystem.theme.PrimaryBlue

@Composable
fun MainTvDisplayScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightBlueBackground)
    ) {
        // Deep Blue Header Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryBlue)
                .padding(horizontal = 32.dp, vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "CITY GENERAL HOSPITAL — OPD TOKEN DISPLAY",
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "10:45 AM | LAN CONNECTED",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 20.sp
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Left Main Card: NOW SERVING (60% width)
            Card(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "NOW SERVING / abhi ki token",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "T-024",
                        fontSize = 110.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Doctor: Dr. Ahmed", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Department: Cardiology", fontSize = 24.sp, color = Color.Gray)
                    Text(text = "ROOM NUMBER 02", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)

                    Spacer(modifier = Modifier.height(32.dp))

                    // Voice announcement bar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(PrimaryBlue.copy(alpha = 0.1f), RoundedCornerShape(16.dp))
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(Icons.Default.VolumeUp, contentDescription = null, tint = PrimaryBlue, modifier = Modifier.size(32.dp))
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Token T-025, Dr. Ahmed ke Room 2 mein aaiye",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryBlue
                        )
                    }
                }
            }

            // Right Panel: NEXT TOKENS (40% width)
            Card(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    Text(
                        text = "NEXT TOKENS / agle tokens",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryBlue
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    listOf("T-025", "T-026", "T-027", "T-028").forEach { token ->
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            color = PrimaryBlue.copy(alpha = 0.05f),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(token, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = PrimaryBlue)
                                Text("Room 02", fontSize = 18.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
        }
    }
}
