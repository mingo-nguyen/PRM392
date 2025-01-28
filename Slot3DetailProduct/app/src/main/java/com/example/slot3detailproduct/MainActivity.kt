package com.example.slot3detailproduct

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.slot3detailproduct.ui.theme.Slot3DetailProductTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Slot3DetailProductTheme {
                ProductDetailScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Details") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            ProductImage(R.drawable.circle) // Replace with your image resource
            Spacer(modifier = Modifier.height(16.dp))
            ProductName("Awesome Product") // Replace with your product name
            Spacer(modifier = Modifier.height(8.dp))
            ProductPrice("$99.99") // Replace with your product price
            Spacer(modifier = Modifier.height(16.dp))
            ProductDescription("This is a detailed description of the awesome product. It has many features and benefits that you will love. This is a detailed description of the awesome product. It has many features and benefits that you will love. This is a detailed description of the awesome product. It has many features and benefits that you will love.") // Replace with your product description
            Spacer(modifier = Modifier.height(24.dp))
            AddToCartButton()
        }
    }
}
@Composable
fun ProductImage(imageResId: Int) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = "Product Image",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ProductName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProductPrice(price: String) {
    Text(
        text = price,
        style = MaterialTheme.typography.headlineSmall,
        color = Color.Green,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun ProductDescription(description: String) {
    Text(
        text = description,
        style = MaterialTheme.typography.bodyLarge,
        lineHeight = 24.sp
    )
}

@Composable
fun AddToCartButton() {
    Button(
        onClick = { /* Handle add to cart action */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text("Add to Cart", fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
    Slot3DetailProductTheme {
        ProductDetailScreen()
    }
}