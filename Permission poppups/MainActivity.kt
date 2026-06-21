package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firstapp.ui.CartScreen
import com.example.firstapp.ui.HomeScreen
import com.example.firstapp.ui.ProductDetailScreen
import com.example.firstapp.ui.AllCategoriesScreen
import com.example.firstapp.ui.AllProductsScreen
import com.example.firstapp.ui.theme.FirstAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EcommerceAppNavigation()
                }
            }
        }
    }
}

@Composable
fun EcommerceAppNavigation() {
    val navController = rememberNavController()

    // Permission Popups Logic
    val permissionsToRequest = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    } else {
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            // Handle permission results if needed
        }
    )

    LaunchedEffect(Unit) {
        permissionLauncher.launch(permissionsToRequest)
    }

    NavHost(
        navController = navController, 
        startDestination = "home",
        enterTransition = { fadeIn() + slideInHorizontally { it } },
        exitTransition = { fadeOut() + slideOutHorizontally { -it } },
        popEnterTransition = { fadeIn() + slideInHorizontally { -it } },
        popExitTransition = { fadeOut() + slideOutHorizontally { it } }
    ) {
        composable("home") {
            HomeScreen(
                onProductClick = { productId ->
                    navController.navigate("product_detail/$productId")
                },
                onCartClick = {
                    navController.navigate("cart")
                },
                onSeeAllCategories = {
                    navController.navigate("categories_all")
                },
                onSeeAllProducts = {
                    navController.navigate("all_products")
                }
            )
        }
        composable("product_detail/{productId}") {
            ProductDetailScreen(onBackClick = { navController.popBackStack() })
        }
        composable("cart") {
            CartScreen(onBackClick = { navController.popBackStack() })
        }
        composable("categories_all") {
            AllCategoriesScreen(onBackClick = { navController.popBackStack() })
        }
        composable("all_products") {
            AllProductsScreen(
                onProductClick = { productId ->
                    navController.navigate("product_detail/$productId")
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
