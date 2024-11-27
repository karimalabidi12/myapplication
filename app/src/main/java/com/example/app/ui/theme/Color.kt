package com.example.app.ui.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
//package com.example.app.db

//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import com.example.app.db.FeedReaderContract.SQL_CREATE_ENTRIES
//import com.example.app.db.FeedReaderContract.SQL_DELETE_ENTRIES

//class FeedReaderDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
//    override fun onCreate(db: SQLiteDatabase) {
//        db.execSQL(SQL_CREATE_ENTRIES)
//    }
//
//    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        db.execSQL(SQL_DELETE_ENTRIES)
//        onCreate(db)
//    }
//
//    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//        onUpgrade(db, oldVersion, newVersion)
//    }
//
//    companion object {
//        const val DATABASE_VERSION = 1
//        const val DATABASE_NAME = "FeedReader.db"
//    }
//}

//package com.example.app.db

//import android.provider.BaseColumns

//object FeedReaderContract {
//    object FeedEntry : BaseColumns {
//        const val TABLE_NAME = "entry"
//        const val COLUMN_NAME = "name"
//        const val COLUMN_PRICE = "price"
//        const val COLUMN_IMAGE = "image"
//        const val COLUMN_DESCRIPTION = "description"
//    }
//
//    const val SQL_CREATE_ENTRIES =
//        "CREATE TABLE ${FeedEntry.TABLE_NAME} (" +
//                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
//                "${FeedEntry.COLUMN_NAME} TEXT," +
//                "${FeedEntry.COLUMN_PRICE} REAL," +
//                "${FeedEntry.COLUMN_IMAGE} TEXT," +
//                "${FeedEntry.COLUMN_DESCRIPTION} TEXT)"
//
//    const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${FeedEntry.TABLE_NAME}"
//}

//package com.example.app

//import android.content.ContentValues
//import android.os.Bundle
//import android.provider.BaseColumns
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import com.example.app.db.FeedReaderContract
//import com.example.app.db.FeedReaderDbHelper
//import com.example.app.ui.theme.SQLiteProjectTheme

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val dbHelper = FeedReaderDbHelper(this)
//
//        enableEdgeToEdge()
//        setContent {
//            SQLiteProjectTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Column(
//                        modifier = Modifier
//                            .padding(innerPadding)
//                            .fillMaxSize(),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//
//                        ButtonComposable(text = "Insert", onClick = { insertDB(dbHelper) })
//                        ButtonComposable(text = "Read") {
//                            readDB(dbHelper)
//                        }
//                        ButtonComposable(text = "Delete") {
//                            deleteDB(dbHelper)
//                        }
//                        ButtonComposable(text = "Update") {
//                            updateDB(dbHelper)
//                        }
//
//                    }
//                }
//            }
//        }
//    }
//}

//fun insertDB(dbHelper: FeedReaderDbHelper) {
//    val db = dbHelper.writableDatabase
//
//    val values = ContentValues().apply {
//        put(FeedReaderContract.FeedEntry.COLUMN_NAME, "Product Name")
//        put(FeedReaderContract.FeedEntry.COLUMN_PRICE, 19.99)  // Exemple de prix
//        put(FeedReaderContract.FeedEntry.COLUMN_IMAGE, "image_url_here")  // URL de l'image
//        put(FeedReaderContract.FeedEntry.COLUMN_DESCRIPTION, "Product description here")
//    }
//
//    val newRowId = db?.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values)
//
//    println("Row inserted with ID: $newRowId")
//}

//fun readDB(dbHelper: FeedReaderDbHelper) {
//    val db = dbHelper.readableDatabase
//
//    val projection = arrayOf(
//        BaseColumns._ID,
//        FeedReaderContract.FeedEntry.COLUMN_NAME,
//        FeedReaderContract.FeedEntry.COLUMN_PRICE,
//        FeedReaderContract.FeedEntry.COLUMN_IMAGE,
//        FeedReaderContract.FeedEntry.COLUMN_DESCRIPTION
//    )
//
//    val cursor = db.query(
//        FeedReaderContract.FeedEntry.TABLE_NAME,
//        null,
//        null,
//        null,
//        null,
//        null,
//        null
//    )
//
//    val itemIds = mutableListOf<Long>()
//    val itemNames = mutableListOf<String>()
//    val itemPrices = mutableListOf<Double>()
//    val itemImages = mutableListOf<String>()
//    val itemDescriptions = mutableListOf<String>()
//
//    with(cursor) {
//        while (moveToNext()) {
//            val itemId = getLong(getColumnIndexOrThrow(BaseColumns._ID))
//            val itemName = getString(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME))
//            val itemPrice = getDouble(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_PRICE))
//            val itemImage = getString(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_IMAGE))
//            val itemDescription = getString(getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_DESCRIPTION))
//
//            itemIds.add(itemId)
//            itemNames.add(itemName)
//            itemPrices.add(itemPrice)
//            itemImages.add(itemImage)
//            itemDescriptions.add(itemDescription)
//        }
//    }
//    cursor.close()
//
//    println("Item IDs: $itemIds")
//    println("Item Names: $itemNames")
//    println("Item Prices: $itemPrices")
//    println("Item Images: $itemImages")
//    println("Item Descriptions: $itemDescriptions")
//}

//fun deleteDB(dbHelper: FeedReaderDbHelper) {
//    val db = dbHelper.writableDatabase
//    val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME} LIKE ?"
//    val selectionArgs = arrayOf("Product Name") // Exemple de nom de produit
//    val deletedRows = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, selectionArgs)
//
//    println("Deleted rows: $deletedRows")
//}

//fun updateDB(dbHelper: FeedReaderDbHelper) {
//    val db = dbHelper.writableDatabase
//
//    val values = ContentValues().apply {
//        put(FeedReaderContract.FeedEntry.COLUMN_PRICE, 24.99)  // Exemple de prix mis à jour
//    }
//
//    val selection = "${FeedReaderContract.FeedEntry.COLUMN_NAME} LIKE ?"
//    val selectionArgs = arrayOf("Product Name")
//    val count = db.update(
//        FeedReaderContract.FeedEntry.TABLE_NAME,
//        values,
//        selection,
//        selectionArgs
//    )
//
//    println("Updated rows: $count")
//}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//@Composable
//fun ButtonComposable(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
//    Button(onClick = onClick) {
//        Text(text = text)
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    SQLiteProjectTheme {
//        Greeting("Android")
//    }
//}
//**********************************
/*@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

val CustomLightColors = lightColorScheme(
    primary = Color(0xFF6200EE),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFFAFAFA),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

val CustomDarkColors = darkColorScheme(
    primary = Color(0xFFBB86FC),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun SQLiteProjectTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> CustomDarkColors
        else -> CustomLightColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}*/