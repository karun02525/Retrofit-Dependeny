package com.swipeapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swipeapp.domain.model.ProductModel
import com.swipeapp.domain.usecase.ProductUseCase
import com.swipeapp.network.ResponseStatus
import com.swipeapp.network.response.onFailure
import com.swipeapp.network.response.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductViewModel(
    private val useCase: ProductUseCase

) : ViewModel() {

    private val _productList = MutableStateFlow<ResponseStatus<List<ProductModel>>>(ResponseStatus.Idle("Idle State"))
    val  productList: StateFlow<ResponseStatus<List<ProductModel>>> get() = _productList


    suspend fun getProduct(){
        _productList.emit(ResponseStatus.Loading())
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                useCase.getProduct().onSuccess {
                    _productList.emit(ResponseStatus.Success(it))
                }.onFailure {
                    _productList.emit(ResponseStatus.Error(it.message))
                }
            }
        }
    }

}