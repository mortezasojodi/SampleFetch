package com.msa.samplefetch.ui.ChatList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.msa.domain.entity.MessageEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Composable
fun ChatListPage() {
    val viewModel: ChatListViewModel = hiltViewModel()
    val context = LocalContext.current

    val state by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    var page by remember {
        mutableStateOf(1)
    }

    fun onEndList() {
        ++page
        coroutineScope.launch {
            viewModel.fetchEnd(page)
        }
    }

    LaunchedEffect(context) {
        viewModel.fetch()
    }


    when (val e = state) {
        is ChatListViewModel.ChatState.Loading -> Loading()
        is ChatListViewModel.ChatState.Success -> BuildBody(e.data, { onEndList() })
        else -> Empty()

    }


}

@Composable
fun Loading() {

}

@Composable
fun Empty() {

}

@Composable
fun BuildBody(data: List<MessageEntity>, onEndList: () -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxHeight()
    ) {
        items(data.size) { item ->
            MessageCm(data[item])
        }
        item {
            onEndList()
        }
    }
}

@Composable
fun MessageCm(data: MessageEntity) {

    Row(

        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = if (data.SendByMe) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically

    ) {
        Box(
            modifier = Modifier.fillMaxWidth(0.7f),
            contentAlignment = if (data.SendByMe) Alignment.CenterEnd else Alignment.CenterStart,
        ) {
            Text(
                modifier = Modifier
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(Color.Cyan)
                    .padding(10.dp),
                textAlign = if (data.SendByMe) TextAlign.End else TextAlign.Start,
                text = data.Text
            )
        }

    }

}