package open.automate.shoppingapp.data

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import open.automate.shoppingapp.ShoppingItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShoppingRepository(context: Context) {
    private val dbHelper = ShoppingDatabaseHelper(context)

    suspend fun loadAllItems(): List<ShoppingItem> = withContext(Dispatchers.IO) {
        dbHelper.getAllShoppingItems()
    }

    suspend fun addItem(name: String, quantity: Int): Long = withContext(Dispatchers.IO) {
        dbHelper.insertShoppingItem(name, quantity)
    }

    suspend fun updateItem(item: ShoppingItem): Int = withContext(Dispatchers.IO) {
        dbHelper.updateShoppingItem(item)
    }

    suspend fun deleteItem(id: Int): Int = withContext(Dispatchers.IO) {
        dbHelper.deleteShoppingItem(id)
    }
}