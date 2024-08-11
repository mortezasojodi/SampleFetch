package com.msa.samplefetch.Product.ProductList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.msa.domain.entity.ProductEntity
import com.msa.samplefetch.Navigation.Routes

@Composable
fun ProductList(
    navController: NavHostController
) {
    val viewModel: ProductListFlowViewModel = hiltViewModel()
    val context = LocalContext.current

    LaunchedEffect(context) {
        viewModel.fetch()
    }
    val state by viewModel.uiState.collectAsState()
//    val uiState = viewModel.uiState.value
    when (val s = state) {
        is ProductListFlowViewModel.ProductListState.Loading -> LoadingCm()
        is ProductListFlowViewModel.ProductListState.Success -> BuildBody(
            s.data,
            navController
        )

        else -> {
            EmptyCm()
        }

    }

}

@Composable
fun LoadingCm() {
    Column {
        Text(text = "Loading")
    }
}

@Composable
fun BuildBody(
    models: List<ProductEntity>,
    navController: NavHostController
) {
    LazyColumn() {
        itemsIndexed(items = models, key = { _, product -> product.id })
        { index, product ->
            BoxItem(product, navController)

        }
    }
}

@Composable
fun BoxItemStack(model: ProductEntity) {
    Box(modifier = Modifier.fillMaxWidth(1f)) {

        Text(
            text = "Hello",
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = "Hello again",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(10.dp),
        )
    }

}

@Composable
private fun Top(modifier: Modifier) {
    Column(modifier.background(Color.Blue)) {
        Text(text = "ss")
    }
}

@Composable
private fun Bottom(modifier: Modifier) {
    Column(modifier.background(Color.White)) {
        Text(text = "ss")
    }
}

@Composable
fun Center(modifier: Modifier) {
    Column(modifier.background(Color.Red, shape = RoundedCornerShape(10.dp))) {
        Text(text = "ss")
    }
}

@Composable
fun BoxItem(model: ProductEntity, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Magenta)
            .padding(10.dp)
            .clickable(onClick = {
                navController.navigate(Routes.DetailDataClass(model))
            })


    ) {
        Column {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(model.image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            VerGap(10.dp)

            Text(
                text = model.title,
                maxLines = 1,
                overflow = TextOverflow.Clip
            )
        }
    }
}


@Composable
fun EmptyCm() {
    Column {
        Text(text = "Empty")
    }
}

@Composable
fun HorGap(value: Dp) {
    Spacer(
        modifier = Modifier
            .width(value)
    )
}

@Composable
fun VerGap(value: Dp) {
    Spacer(
        modifier = Modifier
            .height(value)
    )
}