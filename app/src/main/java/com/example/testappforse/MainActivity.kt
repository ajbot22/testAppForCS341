package com.example.testappforse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testappforse.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val jsonStr = retrieveJsonFromUrl()
        //val data = parseJsonToDataModel(jsonStr)

        val data = getTestDataModel()

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemAdapter(data)
        recyclerView.adapter = adapter


        //TODO: (Open) Should show only 2 buttons, one for add and one for checkout
        //TODO: (Gio) Add should go to its own fragment where you can select from items in the data
        //TODO: (Gio) Add should return to the main activity and put the added items into the recycler view (try using shared preferences w/ Strings and put those strings into the parseJsonToDataModel function I made)
        //TODO: (Toma) Add swipe to delete functionality to the recylcer view. It should decrease the quantity by 1 and if it hits 0, remove the item from the cart
        val swipeHandler = object : SwipeToDeleteCallback(adapter) {
            override fun getSwipeDirs(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                return super.getSwipeDirs(recyclerView, viewHolder)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)


        //TODO: (Open) Checkout should delete all of the items from the cart
        //TODO: (Open) Checkout should also access the database itself to modify the data there when called

        /*
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                //TODO: Rename and change the images for these buttons to be add,remove,checkout
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
         */
    }
    private fun getTestDataModel(): MutableList<DataModel> {
        return mutableListOf(
            DataModel("12345", "Example Product 1", "10", "6789", "example1.jpg"),
            DataModel("67890", "Example Product 2", "20", "1234", "example2.jpg")
        )
    }

}

data class DataModel(
    val productID: String,
    val productName: String,
    val quantity: String,
    val catID: String,
    val img: String
)
fun retrieveJsonFromUrl(): String {
    val url = URL("https://bluejaypantry.etowndb.com/data_src/data.php?table=products")
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    connection.connect()

    return connection.inputStream.bufferedReader().use { it.readText() }
}
fun parseJsonToDataModel(jsonData: String): List<DataModel> {
    val gson = Gson()
    val listType = object : TypeToken<List<DataModel>>() {}.type
    val dataModels = gson.fromJson<List<DataModel>>(jsonData, listType)
    println(dataModels) //error checking
    return dataModels}
