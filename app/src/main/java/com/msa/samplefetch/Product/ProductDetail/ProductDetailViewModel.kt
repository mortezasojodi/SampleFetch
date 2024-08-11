package com.msa.samplefetch.Product.ProductDetail

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.msa.domain.entity.ProductEntity
import com.msa.samplefetch.Navigation.Routes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val savedStateHandle: SavedStateHandle) :
    ViewModel() {

    val product = savedStateHandle.toRoute<Routes.DetailDataClass>()
    var datamodel = MutableStateFlow<ProductEntity?>(null)


    init {
        datamodel.value = product.toEntity()

//        viewModelScope.launch {
//            delay(3000)
//            datamodel.emit(null)
//        }
    }

}