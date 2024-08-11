package com.msa.samplefetch.ui.ChatList

import androidx.lifecycle.ViewModel
import com.msa.domain.Command.PagerCommand
import com.msa.domain.entity.MessageEntity
import com.msa.domain.usecase.product.Chat.ChatGetAllPaginate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(val usecase: ChatGetAllPaginate) : ViewModel() {

    sealed class ChatState {
        data class Success(var data: List<MessageEntity>, var EndLoading: Boolean) : ChatState()
        data object Loading : ChatState()
        data object Empty : ChatState()
    }

    private val _uiState: MutableStateFlow<ChatState> =
        MutableStateFlow<ChatState>(ChatState.Loading)

    val uiState = _uiState.asStateFlow()


    private val _models: List<MessageEntity> = listOf()


    var command: PagerCommand = PagerCommand()


    suspend fun fetchEnd(page: Int) {
        _uiState.emit(
            ChatState.Success(
                data = (_uiState.value as ChatState.Success).data,
                EndLoading = true
            )
        )
        fetch(page)
    }

    suspend fun fetch(page: Int = 1) {
        command.Page = page
        val result = usecase.invoke(command)
        result.onSuccess {
            if (_uiState.value is ChatState.Loading && it.isEmpty()) {
                _uiState.emit(ChatState.Empty)
            } else if ((_uiState.value is ChatState.Success) && (_uiState.value as ChatState.Success).EndLoading) {
                val tempData = ArrayList((_uiState.value as ChatState.Success).data)
                tempData.addAll(it)
                _uiState.emit(ChatState.Success(tempData, false))
            } else {
                _uiState.emit(ChatState.Success(it, false))
            }
        }.onFailure {
            _uiState.emit(ChatState.Empty)
        }


    }


}