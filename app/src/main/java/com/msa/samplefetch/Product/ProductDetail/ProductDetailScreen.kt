package com.msa.samplefetch.Product.ProductDetail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.asFloatState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.msa.domain.entity.ProductEntity
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun ProductDetailScreen(
    navController: NavHostController,
) {
    val viewModel: ProductDetailViewModel = hiltViewModel()
    val model by viewModel.datamodel.collectAsState()

    var isVisible by remember {
        mutableStateOf(true)
    }

    val alpha by animateFloatAsState(targetValue = if (isVisible) 1f else 0.5f, label = "")

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = model?.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth(alpha)
                    .fillMaxHeight(0.5f)
            )
        }
        Text(text = model?.title ?: "")

        Button(onClick = {
            isVisible = if (isVisible) {
                (false)
            } else {
                (true)
            }

        }) {
            Text(text = "Switch")
        }
    }


}