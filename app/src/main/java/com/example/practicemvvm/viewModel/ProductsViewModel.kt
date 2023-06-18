package com.example.practicemvvm.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.practicemvvm.data.repository.ProductsRepository
import com.example.practicemvvm.utils.Resource
import kotlinx.coroutines.Dispatchers

class ProductsViewModel(private val repository: ProductsRepository): ViewModel() {

    fun getProducts() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try{
            emit(Resource.Success(repository.getProducts()))
        } catch(e: Exception){
            emit(Resource.Failure(e))
        }
    }
}

class ProductViewModelFactory(private val repo: ProductsRepository): ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ProductsRepository::class.java).newInstance(repo)
    }

}