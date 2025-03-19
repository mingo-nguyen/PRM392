package open.automate.shoppingapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import open.automate.shoppingapp.ShoppingItem

class ShoppingDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "shopping_list.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_SHOPPING = "shopping_items"

        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_QUANTITY = "quantity"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_SHOPPING (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT NOT NULL,
                $COLUMN_QUANTITY INTEGER NOT NULL
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_SHOPPING")
        onCreate(db)
    }

    fun insertShoppingItem(name: String, quantity: Int): Long {
        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_QUANTITY, quantity)
        }
        return writableDatabase.use { db ->
            db.insert(TABLE_SHOPPING, null, values)
        }
    }

    fun updateShoppingItem(item: ShoppingItem): Int {
        val values = ContentValues().apply {
            put(COLUMN_NAME, item.name)
            put(COLUMN_QUANTITY, item.quantity)
        }
        return writableDatabase.use { db ->
            db.update(
                TABLE_SHOPPING,
                values,
                "$COLUMN_ID = ?",
                arrayOf(item.id.toString())
            )
        }
    }

    fun deleteShoppingItem(id: Int): Int {
        return writableDatabase.use { db ->
            db.delete(
                TABLE_SHOPPING,
                "$COLUMN_ID = ?",
                arrayOf(id.toString())
            )
        }
    }

    fun getAllShoppingItems(): List<ShoppingItem> {
        val items = mutableListOf<ShoppingItem>()
        val selectQuery = "SELECT * FROM $TABLE_SHOPPING"

        readableDatabase.use { db ->
            db.rawQuery(selectQuery, null).use { cursor ->
                if (cursor.moveToFirst()) {
                    val idIndex = cursor.getColumnIndex(COLUMN_ID)
                    val nameIndex = cursor.getColumnIndex(COLUMN_NAME)
                    val quantityIndex = cursor.getColumnIndex(COLUMN_QUANTITY)

                    do {
                        val id = cursor.getInt(idIndex)
                        val name = cursor.getString(nameIndex)
                        val quantity = cursor.getInt(quantityIndex)
                        items.add(ShoppingItem(id, name, quantity))
                    } while (cursor.moveToNext())
                }
            }
        }
        return items
    }
}