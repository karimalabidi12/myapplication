package com.example.app
import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.app.db.FeedReaderContract
import com.example.app.db.FeedReaderDbHelper
import com.example.app.ui.theme.SQLiteProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dbHelper = FeedReaderDbHelper(this)

        enableEdgeToEdge()
        setContent {
            SQLiteProjectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        ButtonComposable(text = "Insert", onClick = { insertDB(dbHelper) })
                        ButtonComposable(text = "Read") {
                            readDB(dbHelper)
                        }
                        ButtonComposable(text = "Delete") {
                            deleteDB(dbHelper)
                        }
                        ButtonComposable(text = "Update") {
                            updateDB(dbHelper)
                        }
                    }
                }
            }
        }
    }
}

fun insertDB(dbHelper: FeedReaderDbHelper) {
    val db = dbHelper.writableDatabase

    val values = ContentValues().apply {
        put(FeedReaderContract.FeedEntry.COLUMN_NAME, "Product Name")
        put(FeedReaderContract.FeedEntry.COLUMN_PRICE, 19.99)
        put(FeedReaderContract.FeedEntry.COLUMN_IMAGE, "image_url_here")
        put(FeedReaderContract.FeedEntry.COLUMN_DESCRIPTION, "Product description here")
    }



    val newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)

    println("Row inserted with ID: $newRowId")
}

fun readDB(dbHelper: FeedReaderDbHelper) {
    val db = dbHelper.readableDatabase

    val projection = arrayOf(
        BaseColumns._ID,
        FeedReaderContract.FeedEntry.COLUMN_NAME,
        FeedReaderContract.FeedEntry.COLUMN_PRICE,
        FeedReaderContract.FeedEntry.COLUMN_IMAGE,
        FeedReaderContract.FeedEntry.COLUMN_DESCRIPTION
    )

    val cursor = db.query(
        FeedReaderContract.FeedEntry.TABLE_NAME,
        null,
        null,
        null,
        null,
        null,
        null
    )

    val itemIds = mutableListOf<Long>()
    val itemNames = mutableListOf<String>()
    val itemPrices = mutableListOf<Double>()
    val itemImages = mutableListOf<String>()
    val itemDescriptions = mutableListOf<String>()

    with(cursor) {
        while (moveToNext()) {
            val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
            val itemName = getString(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME))
            val itemPrice = getDouble(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_PRICE))
            val itemImage = getString(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_IMAGE))
            val itemDescription = getString(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_DESCRIPTION))

            itemIds.add(itemId)
            itemNames.add(itemName)
            itemPrices.add(itemPrice)
            itemImages.add(itemImage)
            itemDescriptions.add(itemDescription)
        }
    }
    cursor.close()

    println("Item IDs: $itemIds")
    println("Item Names: $itemNames")
    println("Item Prices: $itemPrices")
    println("Item Images: $itemImages")
    println("Item Descriptions: $itemDescriptions")
}


fun deleteDB(dbHelper: FeedReaderDbHelper) {
    val db = dbHelper.writableDatabase
    val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME} LIKE ?"
    val selectionArgs = arrayOf("Product Name") // Exemple de nom de produit
    val deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs)

    println("Deleted rows: $deletedRows")
}


fun updateDB(dbHelper: FeedReaderDbHelper) {
    val db = dbHelper.writableDatabase

    val values = ContentValues().apply {
        put(FeedReaderContract.FeedEntry.COLUMN_PRICE, 24.99)  // Exemple de prix mis à jour
    }

    val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME} LIKE ?"
    val selectionArgs = arrayOf("Product Name")
    val count = db.update(
        FeedReaderContract.FeedEntry.TABLE_NAME,
        values,
        selection,
        selectionArgs
    )

    println("Updated rows: $count")
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ButtonComposable(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SQLiteProjectTheme {
        Greeting("Android")
    }
}