package com.msa.samplefetch.Product.ProductList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.msa.domain.entity.ProductEntity
import com.msa.domain.usecase.product.GetAllLocalProduct
import com.msa.domain.usecase.product.GetAllProductUsecase
import com.msa.domain.usecase.product.InsertLocalProductUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val usecase: GetAllProductUsecase,
    private val getLocalUsecase: GetAllLocalProduct,
    private val insertLocalUsecase: InsertLocalProductUsecase
) :
    ViewModel() {

    sealed class ProductListState {
        object Loading : ProductListState()
        data class Success(val data: List<ProductEntity>) : ProductListState()
        object Empty : ProductListState()
    }

    val _uiState = mutableStateOf<ProductListState>(ProductListState.Loading)

    val uiState = _uiState
    public val isLoading: Boolean
        get() = uiState.value is ProductListState.Loading

    suspend fun fetch() {
        val resultLocal = getLocalUsecase.invoke()
        resultLocal.onSuccess { data ->
            _uiState.value = ProductListState.Success(data)
        }.onFailure {
            _uiState.value = ProductListState.Empty
        }

        val result = usecase.invoke()
        result.onSuccess { data ->
            _uiState.value = ProductListState.Success(data)
            insertLocalUsecase.invoke(data)
        }.onFailure {
//            _uiState.value = ProductListState.Empty
        }

    }

//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = (this[APPLICATION_KEY] as FoodCategoriesApplication)
//                val foodCategoriesRepository = application.container.foodCategoriesRepository
//                FoodCategoriesViewModel(foodCategoriesRepository = foodCategoriesRepository)
//            }
//        }
//    }

}