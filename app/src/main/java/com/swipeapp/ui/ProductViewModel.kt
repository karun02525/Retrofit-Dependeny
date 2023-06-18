package com.swipeapp.ui

import android.net.Uri
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
import java.io.InputStream

class ProductViewModel(
    private val useCase: ProductUseCase

) : ViewModel() {

    private val _productList = MutableStateFlow<ResponseStatus<List<ProductModel>>>(ResponseStatus.Idle("Idle State"))
    val  productList: StateFlow<ResponseStatus<List<ProductModel>>> get() = _productList

    private val _addProduct = MutableStateFlow<ResponseStatus<Boolean>>(ResponseStatus.Idle("Idle State"))
    val  addProduct: StateFlow<ResponseStatus<Boolean>> get() = _addProduct


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
    suspend fun addProduct(product_name: String, product_type: String, product_price: String, product_tax: String, uri: Uri?=null){
        _addProduct.emit(ResponseStatus.Loading())
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                useCase.invoke( product_name, product_type, product_price, product_tax, uri)
                    .onSuccess { _addProduct.emit(ResponseStatus.Success(it))
                }.onFailure { _addProduct.emit(ResponseStatus.Error(it.message)) }
            }
        }
    }

}