package com.example.firstapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.firstapp.model.Product
import com.example.firstapp.model.ProductViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import android.util.Log
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = viewModel()
) {
    val products = viewModel.products
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoading) {
            CircularProgressIndicator()
        } else if (errorMessage != null) {
            Text(text = "Error: $errorMessage", color = Color.Red, modifier = Modifier.padding(16.dp))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(products) { product ->
                    ProductItem(product)
                }
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(130.dp)
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = product.title,
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = product.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "⭐ ${product.rating.rate}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFFFBC02D)
                    )
                    Text(
                        text = " (${product.rating.count} reviews)",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

                Text(
                    text = product.category,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                
                Text(
                    text = "$${product.price}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

// --- Other examples from your code ---

@Composable
fun Greeting(name: String) {
    val TAG = "LifeCycle Demo"
    Button(
        onClick = {
            Log.d(TAG, "I am Button Click Now")
        },
        modifier = Modifier.padding(40.dp)
    ) {
        Text("Click Me")
    }
}

@Composable
fun LoginButton() {
    val TAG = "LifeCycle Demo"
    var value by remember { mutableStateOf(0) }
    
    Button(
        onClick = {
            value++
            Log.d(TAG, "Button Click $value")
        },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1DA462))
    ) {
        Text(text = "Login $value", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun BasicTextField() {
    val TAG = "LifeCycle Demo"
    var name by remember { mutableStateOf("") }
    TextField(
        value = name,
        onValueChange = {
            Log.d(TAG, "User Latest Value $it")
            name = it
        },
        label = { Text("Enter Your Name") }
    )
}

@Composable
fun StyledTextField() {
    var email by remember { mutableStateOf("") }
    OutlinedTextField(
        value = email,
        onValueChange = { email = it },
        label = { Text("Email Address") },
        placeholder = { Text("you@example.com") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )
}

@Composable
fun ColumnExample() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text("Item 1")
        Text("Item 2")
        Text("Item 3")
    }
}

@Composable
fun RowExample() {
    Row(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Text("Item 1")
        Text("Item 2")
        Text("Item 3")
    }
}
