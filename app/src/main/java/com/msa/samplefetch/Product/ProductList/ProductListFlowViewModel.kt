package com.msa.samplefetch.Product.ProductList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.msa.domain.entity.ProductEntity
import com.msa.domain.usecase.product.GetAllLocalProduct
import com.msa.domain.usecase.product.GetAllProductUsecase
import com.msa.domain.usecase.product.InsertLocalProductUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductListFlowViewModel @Inject constructor(
    private val usecase: GetAllProductUsecase,
    private val getLocalUsecase: GetAllLocalProduct,
    private val insertLocalUsecase: InsertLocalProductUsecase
) : ViewModel() {


    sealed class ProductListState {
        object Loading : ProductListState()
        data class Success(val data: List<ProductEntity>) : ProductListState()
        object Empty : ProductListState()
    }


    val uiState = MutableStateFlow<ProductListState>(ProductListState.Loading)

//    val uiState = _uiState.asStateFlow()

//    internal var result by mutableStateOf(0)
//        private set

    suspend fun fetch() {
//        withContext(context = viewModelScope.coroutineContext) {
        viewModelScope.launch(Dispatchers.Default) {  //
            val result = usecase.invoke()
            result.onSuccess {
                uiState.emit(ProductListState.Success(it))

            }.onFailure {
                uiState.emit(ProductListState.Empty)
            }
        }


    }


}