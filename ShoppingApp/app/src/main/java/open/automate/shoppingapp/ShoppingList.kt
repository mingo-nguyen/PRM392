package open.automate.shoppingapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class  ShoppingItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    var isEditting: Boolean = false
)

@Composable
fun ShoppingListApp(modifier: Modifier = Modifier) {
    var sItems = remember { mutableStateListOf<ShoppingItem>() }
    var showDiaglog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf(0) }



    if (showDiaglog) {
        // Show dialog to add item
        // Add item to list
        AlertDialog(
            onDismissRequest = { showDiaglog = false },
            title = { Text(text = "Add Item") },
            text = {
                Column {
                    // Add input fields for item name and quantity
                    // Add button to save item

                    OutlinedTextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        label = { Text(text = "Item Name") }
                    )

                    OutlinedTextField(
                        value = itemQuantity.toString(),
                        onValueChange = { itemQuantity = it.toIntOrNull() ?: 0 },
                        label = { Text(text = "Item Quantity") }
                    )

                }
            },
            confirmButton = {

                Button(onClick = { showDiaglog = false;
                   if(itemName.isNotEmpty() && itemQuantity > 0) {
                       var newItem = ShoppingItem(
                           id = sItems.size+1,
                           name = itemName,
                           quantity = itemQuantity)

                        sItems.add(ShoppingItem(sItems.size, itemName, itemQuantity))
                    }
                    itemName = ""
                    itemQuantity = 0


                }) {
                    Text(text = "Save")
                }
            },
            dismissButton = {
                Button(onClick = { showDiaglog = false }) {
                    Text(text = "Cancel")
                }
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                        showDiaglog = true
            }
        ) {
            Text(text = "Add Item")
        }

        LazyColumn(modifier = modifier.fillMaxSize().padding()) { items(sItems) {
            ShoppingListItem(it,{},{})
        } }


    }
}


@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: (ShoppingItem) -> Unit,
    onDeleteClick: (ShoppingItem) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp).border(border= BorderStroke(2.dp, color = androidx.compose.ui.graphics.Color.Black), shape = RoundedCornerShape(20)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.name, Modifier.padding(10.dp))
        Text(text = item.quantity.toString())
        IconButton(onClick = { onEditClick(item) }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = null)

        }
        IconButton(onClick = { onDeleteClick(item) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = null)

        }
    }
}

@Composable
fun ShoppingItemEditor(item: ShoppingItem, onEditComplete: (String, Int) -> Unit) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditting by remember { mutableStateOf(item.isEditting) }
    Row(modifier = Modifier.fillMaxWidth().background(Color.White).padding(8.dp)){
        Column(
             modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            OutlinedTextField(
             value = editedName,
                onValueChange = { editedName = it },
                label = { Text(text = "Item Name") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = editedQuantity,
                onValueChange = { editedQuantity = it },
                label = { Text(text = "Item Quantity") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Button(
                    onClick = {
                        val quantity = editedQuantity.toIntOrNull() ?: 0
                        if(editedName.isNotEmpty() && quantity > 0) {
                            onEditComplete(editedName, quantity)
                            isEditting = false
                        }

                    }
                ){
                    Text(text = "Save")
                }

                Button(
                    onClick = {
                        isEditting = false
                    },
                    colors = buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    )
                ) { Text(text = "Cancel") }


            }

        }
    }


}
