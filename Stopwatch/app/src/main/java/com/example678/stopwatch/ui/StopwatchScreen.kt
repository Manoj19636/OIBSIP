import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example678.stopwatch.viewmodel.StopwatchViewModel

@SuppressLint("DefaultLocale")
@Composable
fun StopwatchScreen(viewModel: StopwatchViewModel = viewModel()) {
    val time by viewModel.time.collectAsState()
    Log.d("StopwatchTime", "Raw time: $time ms")

    val minutes = (time / 60000).toInt()
    val seconds = (time / 1000 % 60).toInt()
    val millis = (time % 1000 / 10).toInt()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB2FEFA).copy(alpha = 0.8f),
                        Color(0xFF0ED2F7).copy(alpha = 0.8f),
                        Color(0xFF4A00E0).copy(alpha = 0.7f)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .padding(24.dp)
                    .shadow(24.dp, RoundedCornerShape(32.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.85f),
                                Color(0xFFFCFBFB).copy(alpha = 0.85f)
                            )
                        ),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .border(
                        width = 2.dp,
                        color = Color.White.copy(alpha = 0.7f),
                        shape = RoundedCornerShape(32.dp)
                    )
                    .padding(horizontal = 48.dp, vertical = 36.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = String.format("%02d:%02d.%02d", minutes, seconds, millis),
                    fontSize = 54.sp,
                    color = Color(0xFF222B45),
                    style = MaterialTheme.typography.displayLarge
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            val buttonGradient = Brush.horizontalGradient(
                listOf(Color(0xFF43CEA2), Color(0xFF185A9D))
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { viewModel.start() },
                    shape = RoundedCornerShape(50),
                    elevation = ButtonDefaults.buttonElevation(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 120.dp, height = 56.dp)
                        .background(buttonGradient, RoundedCornerShape(50))
                ) {
                    Text("Start", fontSize = 20.sp)
                }
                Button(
                    onClick = { viewModel.pause() },
                    shape = RoundedCornerShape(50),
                    elevation = ButtonDefaults.buttonElevation(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 120.dp, height = 56.dp)
                        .background(buttonGradient, RoundedCornerShape(50))
                ) {
                    Text("Pause", fontSize = 20.sp)
                }
                Button(
                    onClick = { viewModel.reset() },
                    shape = RoundedCornerShape(50),
                    elevation = ButtonDefaults.buttonElevation(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .size(width = 120.dp, height = 56.dp)
                        .background(buttonGradient, RoundedCornerShape(50))
                ) {
                    Text("Reset", fontSize = 20.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StopwatchScreenPreview() {
    MaterialTheme {
        StopwatchScreen()
    }
}