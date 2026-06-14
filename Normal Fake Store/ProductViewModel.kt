package com.example.firstapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapp.data.api.RetrofitClient
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    var products by mutableStateOf<List<Product>>(emptyList())
        private set
    
    var isLoading by mutableStateOf(true)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                products = RetrofitClient.apiService.getProducts()
                isLoading = false
            } catch (e: Exception) {
                errorMessage = e.message ?: "Failed to load products"
                isLoading = false
            }
        }
    }
}
